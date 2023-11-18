package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        addNote = AddNote(fakeRepository)
    }

    @Test
    fun `Add empty title note, return false`() = runBlocking {
        val note = Note(
            title = "",
            content = "Note Content",
            timestamp = 0L,
            color = 0
        )
        try {
            addNote.invoke(note)
        } catch (e: Exception) {
            assertEquals(e.message, "The title of the note can't be empty.")
        }
    }

    @Test
    fun `Add empty content note, return false`() = runBlocking {
        val note = Note(
            title = "Note title",
            content = "",
            timestamp = 0L,
            color = 0
        )
        try {
            addNote.invoke(note)
        } catch (e: Exception) {
            assertEquals(e.message, "The content of the note can't be empty.")
        }
    }

    @Test
    fun `Add note, return true`() = runBlocking {
        val note = Note(
            title = "Note title",
            content = "Note Content",
            timestamp = 0L,
            color = 0
        )
        addNote.invoke(note)
        val notes = fakeRepository.notes
        assertTrue(notes.contains(note))
    }


}