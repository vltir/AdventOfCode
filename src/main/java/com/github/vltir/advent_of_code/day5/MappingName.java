package com.github.vltir.advent_of_code.day5;

public enum MappingName {
    SEED_TO_SOIL_MAP(0),
    SOIL_TO_FERTILIZER_MAP(1),
    FERTILIZER_TO_WATER_MAP(2),
    WATER_TO_LIGHT_MAP(3),
    LIGHT_TO_TEMPERATURE_MAP(4),
    TEMPERATURE_TO_HUMIDITY_MAP(5),
    HUMIDITY_TO_LOCATION_MAP(6);

    final int index;
    MappingName(int index){
        this.index=index;
    }
}
