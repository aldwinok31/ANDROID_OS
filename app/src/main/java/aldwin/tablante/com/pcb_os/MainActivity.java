package aldwin.tablante.com.pcb_os;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import java.text.DecimalFormat;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    boolean setter = true;
    int colorset = 0;
    int colorset2 =0;
// PROCESSOR OBJECT

    public class processor {
        private int burstime;
        private int arrivaltime;
        private int servicetime;
        public processor() {
            super();
        }

        public int getBurstime() {
            return this.burstime;
        }

        public int getArrivaltime() {
            return this.arrivaltime;
        }

        public int getServicetime(){return this.servicetime;}
        public void setBurstime(int burstime) {
            this.burstime = burstime;
        }

        public void setArrivaltime(int arrivaltime) {
            this.arrivaltime = arrivaltime;

        }

        public void  setServicetime(int servicetime){

            this.servicetime = servicetime;
        }

    }


    public class cpuTime {

        private int burstTime;
        private int arrivalTime;
private int serviceTime;
        public cpuTime() {
            super();
        }

        public int getServiceTime(){return serviceTime;}
        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getBurstTime() {
            return burstTime;
        }

        public void setArrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public void setBurstTime(int burstTime) {
            this.burstTime = burstTime;
        }

        public void setServiceTime(int serviceTime) {
            this.serviceTime = serviceTime;
        }
    }

    // -------------------------------------------- OBJECT LIST
    ArrayList<processor> arrayList = new ArrayList<>();
    ArrayList<cpuTime> cpuList = new ArrayList<>();
    // -------------------------------------------- OBJECT TRACER
    int tracer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void createEditText(View view) {

if(setter) {

    Button B = (Button) findViewById(R.id.aya);

    EditText b1 = (EditText) findViewById(R.id.ed1);
    EditText a1 = (EditText) findViewById(R.id.ed2);
    EditText s1 = (EditText) findViewById(R.id.ed3);

    int burst = editText(b1);
    int Arrival = editText(a1);
    int service = editText(s1);
    createProcessor(burst, Arrival, service);
    if (tracer > 0) {
        B.setEnabled(true);
    }
    print();
    b1.setText("");
    b1.setHint("Burst Time");
    a1.setText("");
    a1.setHint("Arrival Time");
    s1.setText("");
    s1.setHint("Service Time");

}
    }


    public void print() {
        int theBurst;
        int theArrival;
        int theService;
        String outputA;
        String outputB;
        String outputC;
        String tracerOut;
        processor holder = new processor();
        holder = arrayList.get(tracer);
//-----------------------------------Object Values
        theBurst = holder.getBurstime();
        theArrival = holder.getArrivaltime();
        theService = holder.getServicetime();
//----------------------------------- Int To String Value
        outputA = Integer.toString(theBurst);
        outputB = Integer.toString(theArrival);
        outputC = Integer.toString(theService);
        tracerOut = Integer.toString(tracer + 1);


buttonV(outputA,outputB,tracerOut,outputC);
        colorset2++;
        tracer++;
    }


    public int editText(EditText x) {
        int num = Integer.parseInt(x.getText().toString());
        return num;
    }


    public void createProcessor(int y, int x , int z) {
        processor p = new processor();
        p.setArrivaltime(x);
        p.setBurstime(y);
        p.setServicetime(z);
        arrayList.add(p);
    }


    public void createCpu(int y, int x,int z) {
        cpuTime p = new cpuTime();
        p.setArrivalTime(x);
        p.setBurstTime(y);
        p.setServiceTime(z);
        cpuList.add(p);
    }


    public void calC(View view) {
        if(setter) {
            int index = 0;
            double holder = 0;
            double avgholder =0;
            int[] burst = new int[100];
            int[] arrival = new int[100];
            int[] service = new int[100];
            int[] average = new int[100];


            while (index < tracer) {
                processor procholder = arrayList.get(index);
                burst[index] = procholder.getBurstime();
                arrival[index] = procholder.getArrivaltime();
                service[index] = procholder.getServicetime();
                average[index] = textV(procholder.getArrivaltime(), procholder.getServicetime(),index);
                avgholder = (double)average[index] + avgholder;
                index++;
            }
            index = 0;
            while (index < tracer) {
                holder = burst[index] + holder;
                v(burst[index],(int) holder);
                burst[index] = (int)holder;
                colorset++;
                createCpu(burst[index], arrival[index], service[index]);
                index++;
            }
            index=0;
            while (index < tracer){
                holder = (double)average[index] + holder;

                index++;
            }

            finishView(avgholder/tracer);


        }
        setter=false;
    }

    public int textV (int x,int y,int index){
        LinearLayout fin = (LinearLayout) findViewById(R.id.finish1);


        String outputA;
        String outputB;
        String outputAvg;
        String outputIndex;


        int given3 = y-x;
        outputAvg=Integer.toString(given3);
        outputA=Integer.toString(x);
        outputB=Integer.toString(y);
        outputIndex=Integer.toString(index+1);
  TextView line = new TextView(this);

        line.setText("P"+outputIndex+" : "+"               (Service:) "+outputB+" -  (Arrival:) "+outputA+ "      = " + outputAvg +" ");
        line.setBackgroundColor(Color.parseColor("Black"));
        line.setTextColor(Color.parseColor("White"));
        line.setAlpha((float)0.7);
        line.setTextSize(20);

        fin.addView(line);

return given3;
    }


public void finishView(double x){

    LinearLayout fin = (LinearLayout) findViewById(R.id.finish1);
    String outputA;
    outputA = Double.toString(x);

    TextView line = new TextView(this);
    line.setText("---------------- Average Waiting Time: " + outputA + " ----------------");
    line.setTextSize(20);
    line.setBackgroundColor(Color.parseColor("Red"));
    line.setTextColor(Color.parseColor("White"));
    line.setAlpha((float)0.7);
    fin.addView(line);

}




public void buttonV(String outputA,String outputB,String tracerOut,String outputC){

    float x = (float) 0.5;
    LinearLayout fin = (LinearLayout) findViewById(R.id.fin);
    TextView proc = new TextView(this);
    proc.setEnabled(false);
    proc.setTextColor(Color.parseColor("White"));
    switch(colorset2){

        case 0:proc.setBackgroundColor(Color.parseColor("Black")); break;
        case 1:proc.setBackgroundColor(Color.parseColor("Red"));break;
        case 2:proc.setBackgroundColor(Color.parseColor("Blue"));break;
        case 3:proc.setBackgroundColor(Color.parseColor("Purple"));break;
        case 4:proc.setBackgroundColor(Color.parseColor("Yellow"));break;
        case 5:proc.setBackgroundColor(Color.parseColor("Green"));break;
        default:break;
    }

    proc.setAlpha(x);
    proc.setTextSize(20);

    proc.setText("P" + tracerOut + " : Burst TIME" + " | " + "Arrival TIME" + " | " +"Service TIME" +"\n"+
            "                 "+outputA+"                     "+outputB+"                         "+outputC);
    fin.addView(proc);


}


public void clearValue(View v){
LinearLayout linearParent = (LinearLayout) findViewById(R.id.finish);
    LinearLayout linearParent1 = (LinearLayout) findViewById(R.id.finish1);
    LinearLayout linearParent2 = (LinearLayout) findViewById(R.id.fin);
    linearParent.removeAllViews();
    linearParent1.removeAllViews();
    linearParent2.removeAllViews();
    arrayList.clear();
    cpuList.clear();
    colorset2=0;
    colorset=0;

    tracer=0;
    setter=true;


}

public void v(int x,int y){
    int count=0;
    while(count<x) {
        LinearLayout fin = (LinearLayout) findViewById(R.id.finish);
       TextView line = new TextView(this);
        switch(colorset){
            case 0:line.setBackgroundColor(Color.parseColor("Black")); break;
            case 1:line.setBackgroundColor(Color.parseColor("Red"));break;
            case 2:line.setBackgroundColor(Color.parseColor("Blue"));break;
            case 3:line.setBackgroundColor(Color.parseColor("Purple"));break;
            case 4:line.setBackgroundColor(Color.parseColor("Yellow"));break;
            case 5:line.setBackgroundColor(Color.parseColor("Green"));break;
            default:break;
        }

        line.setTextColor(Color.parseColor("White"));
        line.setAlpha((float)0.7);
if(count == x-1){
    String outputCC;
    outputCC = Integer.toString(y);
    line.setText(outputCC);}

else{  line.setText("   ");}
         line.setTextSize(20);
        fin.addView(line);
        count++;
    }

}




public void SJF(){



}




}









