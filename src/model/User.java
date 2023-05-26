package model;


import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class User implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 8312128879020914324L;
	private String username;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private ArrayList<Projet> projects = new ArrayList<Projet>();
    private Planner planner = new Planner();


    public User() {
        this.username = "";
    }
    public User(String name)
    {this.username = name;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public ArrayList<Projet> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Projet> projectList) {
        this.projects = projectList;
    }
  //__________________Planification________________________

 // __________________Simple Task (sans préciser la periode)_____________________

 public boolean planifier (Task task )

 {

 LocalDate startday=LocalDate.now();

 LocalDate endday= task.getDeadline();

 TreeMap<LocalDate, TreeSet<TimeSlot>> timeslotslist= this.planner.getCalendar().getTimeByDay();

 Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeslotslist.entrySet().iterator();

 Entry<LocalDate, TreeSet<TimeSlot>> entry;

 TreeSet<TimeSlot> TimeSlot_list;

 LocalDate jour;

 boolean scheduled = false;

 while ((it_map.hasNext() )&& (!scheduled))

 {

 entry=it_map.next();

 jour=entry.getKey();

 //on vérifie si le jour est inclus dans le periode demendé:

 if (jour.plusDays(1).isAfter(startday) && jour.isBefore(endday.plusDays(1)) )

 {

 TimeSlot_list =entry.getValue();

 Iterator<TimeSlot> it = TimeSlot_list.iterator();

 TimeSlot t;

 while (it.hasNext())

 {

 t=it.next();

 if (t.isFree()) {

 // on vérifie esq il est libre

 Duration duration_TS = Duration.between(t.getStartTime(), t.getEndTime());

 duration_TS = Duration.ofSeconds(duration_TS.getSeconds());

 if (task.getDuration() != null && duration_TS != null && ( duration_TS.plusMinutes(1).compareTo(task.getDuration()) > 0 )) // on vérifie esq le durée est suffusante

 {

 LocalTime newstartTime = t.getStartTime().plus(task.getDuration());

 Duration newDur= Duration.between(newstartTime, t.getEndTime());

 newDur = Duration.ofSeconds(newDur.getSeconds());

 if ( newDur.compareTo(this.planner.getMinTimeSlot()) >0 || newDur.compareTo(this.planner.getMinTimeSlot()) ==0)// on vérifie le reste du temps pour creer un nouveau creneau

 {

 TimeSlot[] timeSlots = t.decompose(task.getDuration());

 TimeSlot oldtime = timeSlots[0];

 TimeSlot freetime = timeSlots[1];

 oldtime.setTask(task);

 task.setscheduled(true);

 oldtime.setFree(false);

 this.planner.getCalendar().UpdateTimeSlot(jour, oldtime);

 this.planner.getCalendar().add_TimeSlot(jour, freetime);

 } else {

 TimeSlot time = new TimeSlot(t.getStartTime(), t.getEndTime());

 TimeSlot_list.remove(t);

 time.setTask(task);

 task.setscheduled(true);

 time.setFree(false);

 this.planner.getCalendar().add_TimeSlot(jour, time);

 }

 scheduled=true;

 }

 }

 }

 }

 }

 return scheduled;

 }

 // __________________Simple Task (avec précision de la periode)_____________________

 public boolean planifier (Task task,LocalDate startday,LocalDate endday )

 {

 TreeMap<LocalDate, TreeSet<TimeSlot>> timeslotslist= this.planner.getCalendar().getTimeByDay();

 Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeslotslist.entrySet().iterator();

 Entry<LocalDate, TreeSet<TimeSlot>> entry;

 TreeSet<TimeSlot> TimeSlot_list;

 LocalDate jour;

 boolean scheduled = false;

 while ((it_map.hasNext() )&& (!scheduled))

 {

 entry=it_map.next();

 jour=entry.getKey();

 //on vérifie si le jour est inclus dans le periode demendé:


 if (jour.plusDays(1).isAfter(startday) && jour.isBefore(endday.plusDays(1)) )

 {

 TimeSlot_list =entry.getValue();

 Iterator<TimeSlot> it = TimeSlot_list.iterator();

 TimeSlot t;

 while (it.hasNext())

 {

 t=it.next();

 if (t.isFree()) {

 // on vérifie esq il est libre

 Duration duration_TS = Duration.between(t.getStartTime(), t.getEndTime());

 duration_TS = Duration.ofSeconds(duration_TS.getSeconds());

 if (task.getDuration() != null && duration_TS != null && ( duration_TS.plusMinutes(1).compareTo(task.getDuration()) > 0 )) // on vérifie esq le durée est suffusante

 {

 LocalTime newstartTime = t.getStartTime().plus(task.getDuration());

 Duration newDur= Duration.between(newstartTime, t.getEndTime());

 newDur = Duration.ofSeconds(newDur.getSeconds());

 if ( newDur.compareTo(this.planner.getMinTimeSlot()) >0 || newDur.compareTo(this.planner.getMinTimeSlot()) ==0)// on vérifie le reste du temps pour creer un nouveau creneau

 {

 TimeSlot[] timeSlots = t.decompose(task.getDuration());

 TimeSlot oldtime = timeSlots[0];

 TimeSlot freetime = timeSlots[1];

 oldtime.setTask(task);

 task.setscheduled(true);

 oldtime.setFree(false);

 this.planner.getCalendar().UpdateTimeSlot(jour, oldtime);

 this.planner.getCalendar().add_TimeSlot(jour, freetime);

 } else {

 TimeSlot time = new TimeSlot(t.getStartTime(), t.getEndTime());

 TimeSlot_list.remove(t);

 time.setTask(task);

 task.setscheduled(true);

 time.setFree(false);

 this.planner.getCalendar().add_TimeSlot(jour, time);

 }

 scheduled=true;

 }

 }

 }

 }

 }

 return scheduled;

 }

 // __________________Simple Task(planifier arraylist de task simple)_____________________

 public void planifier(ArrayList<Task> tasks)

 {

 Collections.sort( tasks);

 Iterator<Task> it = tasks.iterator();

 while (it.hasNext())

 {

 this.planifier( it.next());

 }

 }

 // __________________Simple Task(planifier arraylist de task simple avec précision de la période)_____________________

 public void planifier(ArrayList<Task> tasks,LocalDate startday,LocalDate endday )

 {

 Collections.sort( tasks);

 Iterator<Task> it = tasks.iterator();

 while (it.hasNext())

 {

 this.planifier( it.next(), startday, endday);

 }

 }

 // _________________Composed Task_____________________

 public TreeMap<LocalDate,TreeSet<TimeSlot>> findTimeslots(Task_composed task)

 {

 LocalDate startday=LocalDate.now();

 LocalDate endday= task.getDeadline();

 TreeMap<LocalDate,TreeSet<TimeSlot>> list_timeslotsByday= new TreeMap<LocalDate,TreeSet<TimeSlot>>();

 TreeSet <TimeSlot> list_timeslots =new TreeSet <TimeSlot> () ;

 Duration durationTotal = Duration.ofSeconds(0);

 TreeMap<LocalDate, TreeSet<TimeSlot>> timeslotslist= this.planner.getCalendar().getTimeByDay();

 Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeslotslist.entrySet().iterator();

 Entry<LocalDate, TreeSet<TimeSlot>> entry;

 TreeSet<TimeSlot> TimeSlot_list;

 LocalDate jour;

 while ((it_map.hasNext() ))

 {

 entry=it_map.next();

 jour=entry.getKey();

 //on vérifie si le jour est inclus dans le periode demendé:

 if (jour.plusDays(1).isAfter(startday) && jour.isBefore(endday.plusDays(1)) )

 {

 TimeSlot_list =entry.getValue();

 Iterator<TimeSlot> it = TimeSlot_list.iterator();

 TimeSlot t;

 while (it.hasNext())

 {

 t=it.next();

 if (t.isFree())

 {

 durationTotal= durationTotal.plus( Duration.between(t.getStartTime(), t.getEndTime()));

 list_timeslots.add(t);

 if (durationTotal.plusMinutes(1).compareTo(task.getDuration() )> 0) {

 list_timeslotsByday.put(jour, list_timeslots);

 return list_timeslotsByday ;

 }

 }

 }

 list_timeslotsByday.put(jour, list_timeslots);

 }

 }

 return list_timeslotsByday ;

 }

 public void planifier2(TreeMap<LocalDate,TreeSet<TimeSlot>> list_timeslotsByday,Task_composed task )

 {

 int i=0;

 Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = list_timeslotsByday.entrySet().iterator();

 Entry<LocalDate,TreeSet<TimeSlot>> entry;

 LocalDate jour;

 TreeSet<TimeSlot> TimeSlot_list;

 TimeSlot t;

 while (it_map.hasNext())

 {

 entry=it_map.next();

 jour=entry.getKey();

 TimeSlot_list =entry.getValue();

 Iterator<TimeSlot> it = TimeSlot_list.iterator();

 while (it.hasNext())

 {

 t=it.next();

 i++;

 Task_composed [] tasks= task.decompose(Duration.between(t.getStartTime(), t.getEndTime()));

 task=tasks[0];

 TimeSlot time = new TimeSlot(t.getStartTime(), t.getEndTime());

 tasks[1].setNom(tasks[0].getNom()+i);

 time.setTask(tasks[1]);

 time.setFree(false);

 //TimeSlot_list.remove(t);

 TreeSet<TimeSlot> timeSlotsSet = this.planner.getCalendar().getTimeByDay() .get(jour);

 timeSlotsSet .remove(t);

 this.planner.getCalendar().add_TimeSlot(jour, time);

 }

 }

 }

 public void planifierComposed(Task_composed task)

 {

 TreeMap<LocalDate,TreeSet<TimeSlot>> list_timeslots= this.findTimeslots(task);

 this.planifier2( list_timeslots,task);
 task.setscheduled(true);

 }

 //__________________________________________________

 public void planifier()

 {

 Iterator<Task> it = tasks.iterator();

 while (it.hasNext())

 {

 this.planifierComposed( (Task_composed) it.next());

 }

 }

//pour un plage du date:

public TreeMap<LocalDate,TreeSet<TimeSlot>> findTimeslots(Task_composed task, LocalDate startday,LocalDate endday)

{

TreeMap<LocalDate,TreeSet<TimeSlot>> list_timeslotsByday= new TreeMap<LocalDate,TreeSet<TimeSlot>>();

TreeSet <TimeSlot> list_timeslots =new TreeSet <TimeSlot> () ;

Duration durationTotal = Duration.ofSeconds(0);

TreeMap<LocalDate, TreeSet<TimeSlot>> timeslotslist= this.planner.getCalendar().getTimeByDay();

Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeslotslist.entrySet().iterator();

Entry<LocalDate, TreeSet<TimeSlot>> entry;

TreeSet<TimeSlot> TimeSlot_list;

LocalDate jour;

while ((it_map.hasNext() ))

{

entry=it_map.next();

jour=entry.getKey();

//on vérifie si le jour est inclus dans le periode demendé:

if (jour.plusDays(1).isAfter(startday) && jour.isBefore(endday.plusDays(1)) )

{

TimeSlot_list =entry.getValue();

Iterator<TimeSlot> it = TimeSlot_list.iterator();

TimeSlot t;

while (it.hasNext())

{

t=it.next();

if (t.isFree())

{

durationTotal= durationTotal.plus( Duration.between(t.getStartTime(), t.getEndTime()));

list_timeslots.add(t);

if (durationTotal.plusMinutes(1).compareTo(task.getDuration() )> 0) {

list_timeslotsByday.put(jour, list_timeslots);

return list_timeslotsByday ;

}

}

}

list_timeslotsByday.put(jour, list_timeslots);

}

}

return list_timeslotsByday ;

}

public void planifierComposed(Task_composed task, LocalDate startday,LocalDate endday)

{

TreeMap<LocalDate,TreeSet<TimeSlot>> list_timeslots= this.findTimeslots(task, startday, endday);

this.planifier2( list_timeslots,task);
task.setscheduled(true);

}

public void afficher_creneau()

{

planner.afficher_Calendar();

}}
