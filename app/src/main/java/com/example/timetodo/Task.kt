package com.example.game

class Task (var userNameFrom:  String,
            var userNameTo: String,
            var name: String,
            var priority: String,
            var description: String,
            var date: String,
            var status: String,
            var tag: String )
{
    companion object {
        var globalTaskName = "anon";
        var globalTaskValue =  "anon";
        var globalTaskType =  "anon";
    }
}