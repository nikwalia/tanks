package io;

public class SystemPacket
{
    private boolean callFire;
    private int callTurn;
    private int callMove;
    private int callGunTurn;
    
    public SystemPacket(boolean fire, int turn, int move, int gunTurn)
    {
        callFire = fire;
        callTurn = turn;
        callMove = move;
        callGunTurn = gunTurn;
    }
    
    public boolean calledFire()
    {
        return callFire;
    }
    
    public int calledTurn()
    {
        return callTurn;
    }
    
    public int calledMove()
    {
        return callMove;
    }
    
    public int calledGunTurn()
    {
        return callGunTurn;
    }
    
}
