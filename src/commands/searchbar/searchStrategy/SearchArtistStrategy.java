package commands.searchbar.searchStrategy;

import client.Session;
import database.Searchable;
import database.users.Artist;
import database.users.User;
import fileio.input.CommandInput;
import fileio.input.FiltersInput;
import java.util.ArrayList;

import static utils.Constants.SEARCH_MAX_RESULT_SIZE;

public class SearchArtistStrategy implements ISearchStrategy {
    private final Session session;
    private final CommandInput commandInput;
    private final User user;

    /* Constructor */
    public SearchArtistStrategy(final Session session, final CommandInput commandInput,
                                 final User user) {
        this.session = session;
        this.commandInput = commandInput;
        this.user = user;
    }

    @Override
    public void search() {
        ArrayList<Searchable> searchResult = user.getSearchResult();
        FiltersInput filtersInput = commandInput.getFilters();

        // Add all artists, then remove those that do not respect the given filters.
        if (filtersInput.getName() == null) {
            return;
        }

        for (Artist artist : session.getDatabase().getArtists()) {
            if (artist.getUsername().startsWith(filtersInput.getName())) {
                searchResult.add(artist);
            }
        }

        while (searchResult.size() > SEARCH_MAX_RESULT_SIZE) {
            searchResult.remove(searchResult.size() - 1);
        }
    }
}
