package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

public class Calendar implements Serializable {
    private static final long serialVersionUID = -8297802628843331249L;
    // Attributs:
    private TreeMap<LocalDate, TreeSet<TimeSlot>> timeByDay;

    // M�thodes:
    /**
     * Constructeur par d�faut de la classe Calendar.
     */
    public Calendar() {
    }

    /**
     * Constructeur de la classe Calendar.
     *
     * @param timeByDay TreeMap associant les dates aux ensembles de cr�neaux horaires.
     */
    public Calendar(TreeMap<LocalDate, TreeSet<TimeSlot>> timeByDay) {
        this.timeByDay = timeByDay;
    }

    /**
     * Retourne le TreeMap associant les dates aux ensembles de cr�neaux horaires.
     *
     * @return Le TreeMap associant les dates aux ensembles de cr�neaux horaires.
     */
    public TreeMap<LocalDate, TreeSet<TimeSlot>> getTimeByDay() {
        return timeByDay;
    }

    /**
     * Modifie le TreeMap associant les dates aux ensembles de cr�neaux horaires.
     *
     * @param timeByDay Le nouveau TreeMap associant les dates aux ensembles de cr�neaux horaires.
     */
    public void setTimeByDay(TreeMap<LocalDate, TreeSet<TimeSlot>> timeByDay) {
        this.timeByDay = timeByDay;
    }

    /**
     * Ajoute un cr�neau horaire � la date sp�cifi�e.
     *
     * @param jour      La date du cr�neau horaire.
     * @param timeSlot  Le cr�neau horaire � ajouter.
     */
    public void add_TimeSlot(LocalDate jour, TimeSlot timeSlot) {
        TreeSet<TimeSlot> timeSlotList = timeByDay.get(jour);
        if (timeSlotList == null) {
            timeSlotList = new TreeSet<TimeSlot>();
            timeSlotList.add(timeSlot);
            timeByDay.put(jour, timeSlotList);
        } else {
            if (estChevauchee(timeSlot, timeSlotList) == false) {
                timeSlotList.add(timeSlot);
            } else {
                System.out.println("erreur, il y a un chevauchement!!");
            }
        }
    }

    /**
     * Met � jour un cr�neau horaire � partir de la date de d�but sp�cifi�e.
     *
     * @param startDay  La date de d�but du cr�neau horaire � mettre � jour.
     * @param timeSlot  Le nouveau cr�neau horaire.
     */
    public void UpdateTimeSlot(LocalDate startDay, TimeSlot timeSlot) {
        TreeSet<TimeSlot> timeSlotList = timeByDay.get(startDay);
        if (timeSlotList == null) {
            System.out.print("Here");
            timeSlotList = new TreeSet<>();
            timeByDay.put(startDay, timeSlotList);
        }
        timeSlotList.remove(timeSlot);
        timeSlotList.add(timeSlot);
    }

    /**
     * V�rifie si un cr�neau horaire chevauche un ensemble de cr�neaux horaires.
     *
     * @param timeSlot      Le cr�neau horaire � v�rifier.
     * @param timeSlotList  L'ensemble de cr�neaux horaires.
     * @return              Vrai s'il y a chevauchement, sinon faux.
     */
    public boolean estChevauchee(TimeSlot timeSlot, TreeSet<TimeSlot> timeSlotList) {
        // Nous aurons un chevauchement dans ces deux cas :
        // 1. L'heure de d�but du cr�neau est entre le d�but et la fin d'un autre cr�neau.
        // 2. L'heure de fin du cr�neau est entre le d�but et la fin d'un autre cr�neau.
        boolean chevauche = false;
        Iterator<TimeSlot> it = timeSlotList.iterator();
        while (it.hasNext()) {
            TimeSlot c = it.next();
            c.afficher();
            LocalTime hD = c.getStartTime();
            LocalTime hF = c.getEndTime();
            LocalTime D = timeSlot.getStartTime();
            LocalTime F = timeSlot.getEndTime();
            if (((hD.isBefore(D)) && (D.isBefore(hF))) || ((hD.isBefore(F)) && (F.isBefore(hF)))) {
                chevauche = true;
                break;
            }
        }
        return chevauche;
    }

    /**
     * Affiche les cr�neaux horaires du calendrier.
     */
    public void afficher() {
        Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeByDay.entrySet().iterator();
        Entry<LocalDate, TreeSet<TimeSlot>> entry;
        LocalDate jour;
        TreeSet<TimeSlot> timeSlotList;
        while (it_map.hasNext()) {
            entry = it_map.next();
            jour = entry.getKey();
            System.out.println("_____________Le jour: " + jour + "_______________");
            timeSlotList = entry.getValue();
            Iterator<TimeSlot> it = timeSlotList.iterator();
            while (it.hasNext()) {
                it.next().afficher();
            }
        }
    }
}
