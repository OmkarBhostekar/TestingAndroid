package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var getNote: GetNote
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        addNote = AddNote(fakeRepository)
        getNote = GetNote(fakeRepository)
    }

    @Test
    fun `Get note, return true`() = runBlocking {
        val id = 10
        val note = Note(
            title = "Note title",
            content = "Note Content",
            timestamp = 0L,
            color = 0,
            id = id
        )
        addNote.invoke(note)
        val notes = fakeRepository.notes
        assertTrue(notes.contains(note))
        val note2 = getNote.invoke(id)
        assertEquals(note, note2)
    }

    @Test
    fun `Get wrong note, return false`() = runBlocking {
        val id = 10
        val note = Note(
            title = "Note title",
            content = "Note Content",
            timestamp = 0L,
            color = 0,
            id = id
        )
        val note2 = Note(
            title = "Note title 2",
            content = "Note Content 2",
            timestamp = 0L,
            color = 0,
            id = id + 1
        )
        addNote.invoke(note)
        val notes = fakeRepository.notes
        assertTrue(notes.contains(note))
        val note3 = getNote.invoke(id + 1)
        assertNotEquals(note2, note3)
    }

}