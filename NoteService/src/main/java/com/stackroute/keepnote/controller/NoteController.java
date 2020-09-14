package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
public class NoteController {

	/*
	 * Autowiring should be implemented for the NoteService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */

	private NoteService noteService;
	private ResponseEntity responseEntity;

	@Autowired
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	/*
	 * Define a handler method which will create a specific note by reading the
	 * Serialized object from request body and save the note details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the note created successfully.
	 * 2. 409(CONFLICT) - If the noteId conflicts with any existing user.
	 *
	 * This handler method should map to the URL "/api/v1/note" using HTTP POST method
	 */

	@PostMapping("/api/v1/note")
	public ResponseEntity createNote(@RequestBody Note note) {
		if (noteService.createNote(note)) {
			responseEntity = new ResponseEntity("Note Created", HttpStatus.CREATED);
		} else {
			responseEntity = new ResponseEntity("Noted creation error", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	/*
	 * Define a handler method which will delete a note from a database.
	 * This handler method should return any one of the status messages basis
	 * on different situations:
	 * 1. 200(OK) - If the note deleted successfully from database.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid noteId without {}
	 */

	@DeleteMapping("/api/v1/note/{id}")
	public ResponseEntity deleteNote(@PathVariable("id") String noteId) {
		try {
			responseEntity = new ResponseEntity(noteService.deleteAllNotes(noteId), HttpStatus.OK);
		} catch (NoteNotFoundExeption e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}


	@DeleteMapping("/api/v1/note/{userid}/{noteId}")
	public ResponseEntity deleteNote(@PathVariable("userid") String userId, @PathVariable("noteId") int noteId) {
		if (noteService.deleteNote(userId, noteId)) {
			responseEntity = new ResponseEntity(noteService.deleteNote(userId, noteId), HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity("Note not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	/*
	 * Define a handler method which will update a specific note by reading the
	 * Serialized object from request body and save the updated note details in a
	 * database.
	 * This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 200(OK) - If the note updated successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP PUT method.
	 */

	@PutMapping("/api/v1/note/{userid}/{noteId}")
	public ResponseEntity updateNote(@RequestBody Note note, @PathVariable("userid") String userId, @PathVariable("noteId") int noteId) {
		try {
			responseEntity = new ResponseEntity(noteService.updateNote(note, noteId, userId), HttpStatus.OK);
		} catch (NoteNotFoundExeption e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/*
	 * Define a handler method which will get us the all notes by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations:
	 * 1. 200(OK) - If the note found successfully.
	 *
	 * This handler method should map to the URL "/api/v1/note" using HTTP GET method
	 */

	@GetMapping("/api/v1/note/{userid}/{noteId}")
	public ResponseEntity getNoteById(@PathVariable("userid") String userId, @PathVariable("noteId") int noteId) {
		try {
			responseEntity = new ResponseEntity(noteService.getNoteByNoteId(userId, noteId), HttpStatus.OK);
		} catch (NoteNotFoundExeption e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/*
	 * Define a handler method which will show details of a specific note created by specific
	 * user. This handler method should return any one of the status messages basis on
	 * different situations:
	 * 1. 200(OK) - If the note found successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * This handler method should map to the URL "/api/v1/note/{userId}/{noteId}" using HTTP GET method
	 * where "id" should be replaced by a valid reminderId without {}
	 *
	 */

	@GetMapping("/api/v1/note/{id}")
	public ResponseEntity getNoteById(@PathVariable("id") String userId) {
		return new ResponseEntity(noteService.getAllNoteByUserId(userId), HttpStatus.OK);
	}


}
