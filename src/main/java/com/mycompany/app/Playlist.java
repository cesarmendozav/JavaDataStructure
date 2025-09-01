package com.mycompany.app;

import java.util.*;
class Playlist {
    Deque<String> playNext = new ArrayDeque<>(); // stack
    Queue<String> normal = new LinkedList<>();   // queue
    void addNormal(String song){ normal.add(song); }
    void addPlayNext(String song){ playNext.push(song); }
    String nextTrack(){
        if(!playNext.isEmpty()) return playNext.pop();
        return normal.isEmpty()? null : normal.poll();
    }
}