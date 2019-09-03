package com.example.dell.lawera;

public class CustomItem {
    public String name;
    public String voice_path;
    public byte[] image;

    public CustomItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoice_path() {
        return voice_path;
    }

    public void setVoice_path(String voice_path) {
        this.voice_path = voice_path;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
