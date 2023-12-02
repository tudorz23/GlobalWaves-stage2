package commands.adminCommands;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.audio.Album;
import database.users.Artist;
import database.users.User;

public class DeleteArtistStrategy implements IDeleteUserStrategy {
    private final Session session;
    private final Artist artist;

    /* Constructor */
    public DeleteArtistStrategy(final Session session, final Artist artist) {
        this.session = session;
        this.artist = artist;
    }

    @Override
    public boolean deleteUser() {
        // Check if any user is on artist's official page.
        for (User user : session.getDatabase().getBasicUsers()) {
            if (user.getCurrPage() == artist.getOfficialPage()) {
                return false;
            }
        }

        for (User user : session.getDatabase().getArtists()) {
            if (user.getCurrPage() == artist.getOfficialPage()) {
                return false;
            }
        }

        for (User user : session.getDatabase().getHosts()) {
            if (user.getCurrPage() == artist.getOfficialPage()) {
                return false;
            }
        }

        // Check if any user interacts with any of the artist's albums.
        for (Album album : artist.getAlbums()) {
            for (User user : session.getDatabase().getBasicUsers()) {
                if (user.interactsWithAudio(album)) {
                    return false;
                }
            }

            for (User user : session.getDatabase().getArtists()) {
                if (user.interactsWithAudio(album)) {
                    return false;
                }
            }

            for (User user : session.getDatabase().getHosts()) {
                if (user.interactsWithAudio(album)) {
                    return false;
                }
            }
        }

        for (Album album : artist.getAlbums()) {
            session.getDatabase().removeAlbum(album);
        }

        session.getDatabase().removeUser(artist);
        return true;
    }
}
