package io;

public class SystemPacket
{
    private boolean callFire;
    private int callTurn;
    private int callMove;
    
    public SystemPacket(boolean fire, int turn, int move)
    {
        callFire = fire;
        callTurn = turn;
        callMove = move;
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
}
