package com.example.rohit.apicalling;

public class Astute
{
    private String userId;
    private String id;
    private String title;
    private String body;

    public Astute(String userId, String id, String title, String body)
    {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getBody()
    {
        return body;
    }
}
