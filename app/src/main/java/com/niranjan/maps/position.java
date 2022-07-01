package com.niranjan.maps;

public class position {
    public float X;
    public float Y;
    public position() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public position(float x, float y) {
        this.X = x;
        this.Y = y;
    }
}
