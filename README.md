*Designed by Marius-Tudor Zaharia, 323CA, November-December 2023*

# GlobalWaves - stage 2

---

## Table of contents
1. [What is GlobalWaves?](#what-is-globalwaves)
2. [What is new?](#what-has-been-added-to-the-platform)
3. [I/O](#io)
4. [Implementation details](#implementation-details)
    * [Design choices](#design-choices)
    * [Design patterns used](#design-pattern-used)
    * [Program flow](#program-flow)
5. [Conclusions](#conclusions)

---

## What is GlobalWaves?
* GlobalWaves is a music and podcast streaming service backend written in the
Java language.
* This is the second stage of the main project, extending the first part, so
the present will mostly focus on the new functionalities, while more details on
the first stage, the basics of the platform and the run procedure can be found
[here](https://github.com/tudorz23/GlobalWaves.git).

---

## What has been added to the platform?
* While the base functionalities from stage 1 remain mostly unchanged, there
have been various additions made regarding the audio entities, the users and
the abilities of the administrator.
* The new **user** entities supported are:
  * `artist` - which can add/remove albums and has his own page;
  * `host` - which can add/remove podcasts and also has his own page.
* Now the platform supports `album`, a song collection created and owned by an
artist. It has the same features of a playlist, like repeat, shuffle, next, ...
* `Pages` are now also supported, and are of the following types:
  * `HomePage` - default page of every user;
  * `LikedContentPage` - where users can see their liked songs and playlists;
  * `ArtistPage` - official page that each artist owns;
  * `HostPage` - official page that each host owns.
* Users can now search for albums, artists and hosts.
* Users can toggle their connection status between ON and OFF.
* The database admin can now add and delete users of any kind.

---

## I/O
* It remains unchanged from stage 1, with the **Jackson** library still being
used for parsing the JSON format of input and output files.

---

## Implementation details
### Design choices
* To add support for `Album`, which shares functionalities with `Playlist`,
a new abstract mother class has been created, `SongCollection`, extending the
`Audio` class. It contains the shared behaviours of its two extenders, while
their classes include more specialized characteristics.
* For `Artist` and `Host`, inheritance was again the solution, each of them
extending the `User` class and adding their own features. Another extender,
`BasicUser`, was also added, to better distinguish between content creators
and normal users.
* To solve the problems arising by the added feature of searching artists and
hosts, the abstract class `Searchable` was added, which is extended by the
classes whose objects can be searched via search bar, i.e. `Artist`, `Host` and
`Audio`. An abstract class was chosen instead of an interface for storing the
**type** field, thus `User` class had to extend `Searchable`, because `Artist`
and `Host` already extended `User`. This can be helpful in the event that a
feature that allows the searching of basic users (to befriend them or whatever)
would be added in the third stage. The same result could be obtained with an
interface exposing a `getType()` method overridden by each class implementing
it, so at any point in the third stage the implementation could change to this,
if any class needs to extend another class, and not `Searchable`.
* Artist and Host pages are responsible with storing their albums/podcasts,
serving as the bridge between them and the "fans".
* Java ***records*** have been used for the implementation of `Merch`, `Event`
and `Announcement`, because their objects should be immutable.
* `Exceptions` have been used much better and often than in the first stage,
its messages helping to print the error outputs in a smoother way.
* The `Printer` mechanism has been updated, marking a trade-off between using
some general classes and some more specialized ones (for certain commands),
the main goal being not to burden the classes with too many methods, but also
not to have too many classes, readability and simplicity being sought after.
* Sorting using `Comparator` and method reference operator is used many times,
thus making the code cleaner.

---

### Design pattern used
### Command Pattern
* Kept from the first stage, used for separating the implementations of
different actions. Because of this, adding new functionalities was done in
an easy and "clean" way.
* Still based on the `ICommand` interface and the Invoker class calling the
`execute()` method.

### Strategy Pattern
* Used for diverging in implementation between various ways of searching
(from stage 1), based on the `ISearchStrategy` interface.
* Used for different implementations of deleting a user, depending on its type.
Based on the `IDeleteStrategy` interface.

### Factory Pattern
* Used a Factory Method in three places:
  * to create concrete command instances based on the `ICommand` interface in
    the `CommandFactory` class.
  * to create concrete search strategies based on `ISearchStrategy` in the
    `getSearchStrategy()` method of the `SearchCommand` class.
  * to create delete strategies in the `getDeleteUserStrategy()` method of
  the `DeleteUserCommand` class.
  * to create `Page` instances in the `PageFactory` class.

---

### Program flow
* Similarly to first stage, `AdminInteraction` class is the main client,
iterating through the input commands and using the `CommandFactory` to generate
commands, while `Invoker` serves as an intermediary to call `execute()` method.
* The `Session` class serves as the bridge between the internal data stored
in the Database and the code that uses it.
* Depending on the type of command, actions are performed, usually modifying
the state of the `Player` instance of one `User`.
* Before a new command is applied, the time passing is simulated using the
polymorphic method `simulateTimePass()`.
* The output is then appended using specialized `Printer` objects.

---

## Conclusions
* By implementing this second stage, I learnt:
  * about `LocalDate` and `DateTimeFormatter` functionalities of Java;
  * about Java records and when/how to use them;
  * to use exceptions messages and try/catch mechanism in a smarter,
  smoother way;
  * how to extend a project and how to better plan the design to easily accept
  new features.