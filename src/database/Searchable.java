package database;

import utils.enums.SearchableType;

/**
 * Extended by all objects that can be searched via search bar.
 * Preferred this over a marker interface because an Interface can't have,
 * different field values and, because Audio and User don't have anything
 * in common, the usage of instanceof would have been unavoidable.
 */
public abstract class Searchable {
    private SearchableType searchableType;

    public SearchableType getSearchableType() {
        return searchableType;
    }
    public void setSearchableType(SearchableType searchableType) {
        this.searchableType = searchableType;
    }
}
