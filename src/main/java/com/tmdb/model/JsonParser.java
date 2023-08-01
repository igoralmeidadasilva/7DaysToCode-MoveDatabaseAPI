package com.tmdb.model;

import java.util.List;

public interface JsonParser {
    public List<? extends Content> parse(String[] lista);
}
