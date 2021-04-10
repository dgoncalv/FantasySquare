package Model.Utility;

import java.util.Objects;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D reverseVector()
    {
        return new Vector2D(y, x);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Affiche le vecteur au format (x, y)
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //Si deux vecteur ont x=x' et y=y', alors ils sont égaux
    @Override
    public boolean equals(Object o) {
        if(this == o)
        {
            return true;
        }
        if(o == null)
        {
            return false;
        }
        Vector2D temp = (Vector2D) o;
        return x == temp.getX() && y == temp.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
