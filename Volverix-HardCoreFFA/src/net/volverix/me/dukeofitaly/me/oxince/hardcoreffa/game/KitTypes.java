package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game;

public enum KitTypes {

    TEST,
    OP;

    private static KitTypes currentKit;

    public static KitTypes getCurrentKit() {
        return currentKit;
    }

    public static void setCurrentKit(KitTypes kitTypes) {
        currentKit = kitTypes;
    }


}
