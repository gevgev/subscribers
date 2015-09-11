package service.dbAccess;

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import javax.transaction.Transactional;

	import org.springframework.stereotype.Repository;

	import common.models.Subscriber;

	/**
	 * This class is used to access data for the User entity.
	 * Repository annotation allows the component scanning support to find and 
	 * configure the DAO wihtout any XML configuration and also provide the Spring 
	 * exceptiom translation.
	 * Since we've setup setPackagesToScan and transaction manager on
	 * DatabaseConfig, any bean method annotated with Transactional will cause
	 * Spring to magically call begin() and commit() at the start/end of the
	 * method. If exception occurs it will also call rollback().
	 */
	@Repository
	@Transactional
	public class SubscriberDao {
		  // ------------------------
		  // PUBLIC METHODS
		  // ------------------------
		  
		  /**
		   * Save the user in the database.
		   */
		  public Subscriber create(Subscriber subscriber) {
		    entityManager.persist(subscriber);
		    return subscriber;
		  }
		  
		  /**
		   * Delete the user from the database.
		   */
		  public void delete(Subscriber subscriber) {
		    if (entityManager.contains(subscriber))
		      entityManager.remove(subscriber);
		    else
		      entityManager.remove(entityManager.merge(subscriber));
		    return;
		  }
		  
		  /**
		   * Return all the users stored in the database.
		   */
		  @SuppressWarnings("unchecked")
		  public List<Subscriber> getAll() {
		    return entityManager.createQuery("from Subscriber").getResultList();
		  }
		  
		  /**
		   * Return the user having the passed email.
		   */
		  public Subscriber getByApiKey(String apiKey) {
		    return (Subscriber) entityManager.createQuery(
		        "from Subscriber where apiKey = :apiKey")
		        .setParameter("apiKey", apiKey)
		        .getSingleResult();
		  }

		  /**
		   * Return the user having the passed id.
		   */
		  public Subscriber getById(Long subscriberId) {
		    return entityManager.find(Subscriber.class, subscriberId);
		  }

		  /**
		   * Update the passed user in the database.
		   */
		  public void update(Subscriber subscriber) {
		    entityManager.merge(subscriber);
		    return;
		  }

		  // ------------------------
		  // PRIVATE FIELDS
		  // ------------------------
		  
		  // An EntityManager will be automatically injected from entityManagerFactory
		  // setup on DatabaseConfig class.
		  @PersistenceContext
		  private EntityManager entityManager;

	}

