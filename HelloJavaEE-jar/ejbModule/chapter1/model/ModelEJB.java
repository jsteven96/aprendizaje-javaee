package chapter1.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Session Bean implementation class ModelEJB
 */
@Stateful
@LocalBean
public class ModelEJB {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public void putUserMessage(String messageString) throws MessageException {
		this.deleteMessage();
		
		try {
			String decodedMessage = URLDecoder.decode(messageString, "UTF-8");
			Message message = new Message("1", "(" + decodedMessage + ")" + " in a database");
			EntityManager em = emf.createEntityManager();
			em.persist(message);
			
		} catch (UnsupportedEncodingException e) {
			throw new MessageException("something odd about that message..." + messageString);
		}
	}
	
	public void deleteMessage() {
		EntityManager em = emf.createEntityManager();
		em.createNamedQuery("deleteMessages").executeUpdate();
	}
	
	public String getStoredMessage() throws MessageException {
		EntityManager em = emf.createEntityManager();
		List<?> messages = em.createNamedQuery("findMessages").getResultList();
		if(messages.size() > 0) {
			Message message = (Message) messages.get(0);
			return "(" + message.getMessageString() + "), inside an EJB";
		} else {
			throw new MessageException("There was nothing in the database.");
		}
	}

    /**
     * Default constructor. 
     */
    public ModelEJB() {
        // TODO Auto-generated constructor stub
    }

}
