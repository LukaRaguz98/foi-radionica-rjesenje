package com.example.application.views.zadaci;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Zadatak 1")
@Route(value = "zadatak1", layout = MainLayout.class)
public class Zadatak1View extends VerticalLayout {

    private H1 naslovH1;
    private TextField imeKolegijaText;
    private TextField ocjenaText;
    private Button potvrdiButton;

    public Zadatak1View() {
        createComponents();
        composeView();
    }

    private void createComponents() {
        naslovH1 = new H1("Kolegiji i ocjene - Kreiranje komponenti");

        imeKolegijaText = new TextField("Ime kolegija:");

        ocjenaText = new TextField("Ocjena (1-5):");

        potvrdiButton = new Button("Potvrdi");
        potvrdiButton.addClickListener(e -> {
            String predmet = imeKolegijaText.getValue();
            String ocjena = ocjenaText.getValue();

            if (!predmet.isEmpty() && isOcjenaValid(ocjena)) {
                int ocjenaBroj = Integer.parseInt(ocjena);
                Notification.show("Predmet: " + predmet + " - Ocjena: " + ocjenaBroj);
            } else {
                Notification.show("Molimo unesite ispravne podatke.");
            }
        });
    }

    private void composeView() {
        add(naslovH1);
        add(imeKolegijaText);
        add(ocjenaText);
        add(potvrdiButton);
    }

    private boolean isOcjenaValid(String ocjenaText) {
        try {
            int ocjena = Integer.parseInt(ocjenaText);
            return ocjena >= 1 && ocjena <= 5;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
