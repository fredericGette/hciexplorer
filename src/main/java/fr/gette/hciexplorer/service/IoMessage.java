package fr.gette.hciexplorer.service;

public class IoMessage {
    short[] data;
    int offset;

    IoMessage(short[] data) {
        this.data = data;
        offset = 0;
    }

    short readUChar() {
        return data[offset++];
    }

    int readUShort() {
        int result = 0;
        result += readUChar();
        result += readUChar() << 8;
        return result;
    }

    long readULong() {
        long result = 0;
        result += readUChar();
        result += readUChar() << 8;
        result += readUChar() << 16;
        result += readUChar() << 24;

        return result;
    }
}
