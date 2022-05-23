package com.example.demo.workspace;

public class PathRequest {
    private String path;

    public PathRequest() {
    }

    public PathRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PathRequest{" +
                "path='" + path + '\'' +
                '}';
    }
}
