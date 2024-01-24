package com.azhar.note.dao;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\'J\u0012\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\'R\u001e\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/azhar/note/dao/NoteDao;", "", "allNote", "", "Lcom/azhar/note/model/ModelNote;", "getAllNote", "()Ljava/util/List;", "delete", "", "modelNote", "insert", "app_debug"})
public abstract interface NoteDao {
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM notes ORDER BY id DESC")
    public abstract java.util.List<com.azhar.note.model.ModelNote> getAllNote();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.Nullable
    com.azhar.note.model.ModelNote modelNote);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.Nullable
    com.azhar.note.model.ModelNote modelNote);
}