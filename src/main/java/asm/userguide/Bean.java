package asm.userguide;

public class Bean {
    
    private int f;

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    public void checkAndSetF(int f) {
        if (f >= 0) {
            this.f = f;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
