package com.sample.enums;


public enum PizzaTopping {

    DICED_MANGO("Diced Mango"),
    OLIVES("Olives"),
    MUSHROOMS("Mushrooms"),
    CARAMELIZED_ONIONS("Caramelized onions"),
    ITALIAN_HAM("Italian Ham"),
    CLASSIC_PEPPERONI("Classic Pepperoni"),
    SALAMI("Salami"),
    PROVOLONE_CHEESE("Provolone cheese"),
    EXTRA_CHEESE("Extra cheese"),
    MOZZARELLA_CHEESE("Mozzarella cheese"),
    PARMESAN_CHEESE("Parmesan cheese");

    private String displayName;

    PizzaTopping(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
