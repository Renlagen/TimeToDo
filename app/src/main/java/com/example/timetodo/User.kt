package com.example.game

class User (val name:  String, val email: String, val password: String)
{
    companion object {
        var globalName = "anon";
    }
}