package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeleteNoteTest {


    private lateinit var addNote: AddNote
    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        addNote = AddNote(fakeRepository)
        deleteNote = DeleteNote(fakeRepository)
    }

    @Test
    fun `Delete note, return true`() = runBlocking {
        val note = Note(
            title = "Note title",
            content = "Note Content",
            timestamp = 0L,
            color = 0
        )
        addNote.invoke(note)
        deleteNote.invoke(note)
        val notes = fakeRepository.notes
        assertFalse(notes.contains(note))
    }

    @Test
    fun `Delete wrong note, return false`() = runBlocking {
        val note = Note(
            title = "Note title",
            content = "Note Content",
            timestamp = 0L,
            color = 0
        )
        val note2 = Note(
            title = "Note title 2",
            content = "Note Content 2",
            timestamp = 0L,
            color = 0
        )
        addNote.invoke(note)
        deleteNote.invoke(note2)
        deleteNote.invoke(note2)
        val notes = fakeRepository.notes
        assertTrue(notes.contains(note))
    }
}