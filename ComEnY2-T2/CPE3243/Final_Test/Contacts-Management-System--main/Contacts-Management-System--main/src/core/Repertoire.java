package core;

import java.util.ArrayList;
import java.util.List;

public class Repertoire {
	private List<Contact> contacts;
	
	public Repertoire() {
		this.contacts= new ArrayList<>();
	}
	
	public void ajouterContact(Contact contact) {
		contacts.add(contact);
	}
	
	public boolean supprimerContact (String code) {
		return contacts.removeIf(contact->contact.getCode().equals(code));
	}
	
	public Contact rechercherContact(String code) {
		for (Contact contact : contacts) {
			if(contact.getCode().equals(code)) {
				return contact;
			}
		}
		return null;
	}
	
	public void modifierContact(String code,Contact nouveauContact) {
		int index =-1;
		for(int i=0; i<contacts.size();i++) {
			if (contacts.get(i).getCode().equals(code)){
				index=i;
				break;
			}
		}
		if(index!=-1) {
			contacts.set(index, nouveauContact);
		}
	}
    // Méthode pour afficher tous les contacts dans le répertoire
    public void afficherContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
