package carnet.model;

import java.time.LocalDate;

public class PageContenu extends Page{

    private LocalDate date;

    public PageContenu(LocalDate date) {
        super();
        this.date = date;

    }

    @Override
    public boolean estPageContenu(){
        return true;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}
