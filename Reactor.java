import java.util.Scanner;

public class Reactor
{
    int	level=30;
    int	max_level=100;
    int target = 80;
    int	min_level=0;
    Scanner in = new Scanner(System.in);

    boolean	stopped=false;
    /**
     *	Поток, поднимающий стержень из реактора
     */
    Thread heigh=new Thread()
    {	public void run() {
        while (!stopped) {
            try {
                sleep(100);

                level++;
                //  System.out.println("уровень повышен до "+level);}
                 }
                catch(Exception pe)
                {
                    System.out.println("Error heigh: " + pe);

                }
            }


    }

    };
    /**
     *	Поток, опускающий стержень в реактор
     */
    Thread low=new Thread()
    {	public void run()
    { while(!stopped)
    {	try
    {	sleep(1000);
        if (level>target){
            int t=level-target;
            if (t<0 || t>-10)
                level --;
            if (t<-10 || t>-20)
                level = level - 2;
            if (t<-20 || t>-30)
                level = level - 3;
            if (t>0)
                level = level - 5;
        }

  //      System.out.println("уровень понижен до "+level);}
    }
   catch(Exception pe){

       System.out.println("Error low: " + pe);
    }
    }

    }

    };
    Thread monitor=new Thread() {
            public void run ()
            {
                while (!stopped) {
                    try {
                        sleep (1000);
                        System.out.println("Текущая температура: " + level);
                    } catch (Exception pe) {

                        System.out.println("Error low: " + pe);
                    }
                }
            }
        };
    Thread usin=new Thread() {
        public void run ()
        {
            while (!stopped) {
              try {
                   sleep (1000);

                    System.out.println("Введите необходимую рабочую температуру: ");
                    int num = in.nextInt();
                    target=num;
                    System.out.println(target);

                } catch (Exception pe) {

                    System.out.println("Error low: " + pe);
                }
            }
        }
    };

    /**
     *	Поток, опускающий стержень в реактор
     */
    Thread control=new Thread()
    {	public void run()
    { while(!stopped)
    {	try
    {	sleep(1000);
        if(level>=max_level)
        {	System.out.println("BOOM with "+level);
            stopped=true;
            System.exit(0);
        }
        if(level<=min_level)
        {	System.out.println("OFF with "+level);
            stopped=true;
            System.exit(0);
        }
    }
    catch(Exception pe)
    {
        System.out.println("Error : " + pe);
    }

    }

    }
    };
    /**
     *
     */
    public Reactor()
    {
        control.setPriority(Thread.NORM_PRIORITY);	//NORM_PRIORITY MIN_PRIORITY
        low.setPriority(Thread.NORM_PRIORITY);
        heigh.setPriority(Thread.NORM_PRIORITY);

        control.start();
        heigh.start();
        low.start();
        monitor.start();
        usin.start();
    }
    /**
     *
     */
    public static void main(String args[])
    {	System.out.println("Реактор запущен");
        new Reactor();
    }

}
