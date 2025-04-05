package therealBJ.Players;

import java.io.*;


public class BJplayer implements Serializable
{

    private String name;
    

    private int age;
    

    private String gender;
    
    public BJplayer()
    {
        
    }
    
    public BJplayer(String name, int age, String gender)
    {
        setName(name);
        setAge(age);
        setGender(gender);
    }
    

    public void setName(String name)
    {
        this.name = name;
    }
    

    public String getName()
    {
        return this.name;
    }
    

    public void setAge(int age)
    {
        this.age = age;
    }
    

    public int getAge()
    {
        return this.age;
    }
    

    public void setGender(String gender)
    {
        this.gender = gender;
    }
    

    public String getGender()
    {
        return this.gender;
    }
    

    public String toString()
    {
        return getName();
    }    
} 