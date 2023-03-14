package ru.otus.model.enums;

public enum Nominal {
    RUB_100 {
        public int get() {
            return 100;
        }
    },
    RUB_500 {
        public int get() {
            return 500;
        }
    },
    RUB_1000 {
        public int get() {
            return 1000;
        }
    },
    RUB_5000 {
        public int get() {
            return 5000;
        }
    };
    public abstract int get();
}
