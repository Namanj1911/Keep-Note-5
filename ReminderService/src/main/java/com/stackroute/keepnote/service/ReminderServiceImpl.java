package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently
 * provide any additional behavior over the @Component annotation, but it's a good idea
 * to use @Service over @Component in service-layer classes because it specifies intent
 * better. Additionally, tool support and additional behavior might rely on it in the
 * future.
 * */

@Service
public class ReminderServiceImpl implements ReminderService {
	private ReminderRepository reminderRepository;

	/*
	 * Autowiring should be implemented for the ReminderRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	@Autowired
	public ReminderServiceImpl(ReminderRepository reminderRepository) {
		this.reminderRepository = reminderRepository;
	}

	/*
	 * This method should be used to save a new reminder.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public Reminder createReminder(Reminder reminder) throws ReminderNotCreatedException {
		Optional<Reminder> optionalCategory = reminderRepository.findById(reminder.getReminderId());
		if (optionalCategory.isPresent()) {
			throw new ReminderNotCreatedException("Reminder already exists");
		}
		Reminder reminder1 = reminderRepository.insert(reminder);
		if (reminder1 == null) {
			throw new ReminderNotCreatedException("Reminder not created");
		}
		return reminder1;
	}

	/*
	 * This method should be used to delete an existing reminder.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public boolean deleteReminder(String reminderId) throws ReminderNotFoundException {
		Optional<Reminder> optionalCategory = reminderRepository.findById(reminderId);
		if (optionalCategory.isPresent()) {
			reminderRepository.deleteById(reminderId);
			return true;
		} else {
			throw new ReminderNotFoundException("Reminder does not exist");
		}
	}

	/*
	 * This method should be used to update a existing reminder.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public Reminder updateReminder(Reminder reminder, String reminderId) throws ReminderNotFoundException {
		Optional<Reminder> optionalCategory = reminderRepository.findById(reminderId);
		if (optionalCategory.isPresent()) {
			reminderRepository.save(reminder);
			return reminder;
		}
		return null;
	}

	/*
	 * This method should be used to get a reminder by reminderId.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public Reminder getReminderById(String reminderId) throws ReminderNotFoundException {
		if (reminderRepository.findById(reminderId).isPresent()) {
			return reminderRepository.findById(reminderId).get();
		} else {
			throw new ReminderNotFoundException("Reminder not found");
		}
	}

	/*
	 * This method should be used to get all reminders. Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public List<Reminder> getAllReminders() {
		return reminderRepository.findAll();
	}

}
