package com.azhar.note.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.azhar.note.model.ModelNote;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ModelNote> __insertionAdapterOfModelNote;

  private final EntityDeletionOrUpdateAdapter<ModelNote> __deletionAdapterOfModelNote;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfModelNote = new EntityInsertionAdapter<ModelNote>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `notes` (`id`,`title`,`date_time`,`sub_title`,`note_text`,`image_path`,`color`,`web_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ModelNote value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDateTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDateTime());
        }
        if (value.getSubTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubTitle());
        }
        if (value.getNoteText() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNoteText());
        }
        if (value.getImagePath() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImagePath());
        }
        if (value.getColor() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getColor());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUrl());
        }
      }
    };
    this.__deletionAdapterOfModelNote = new EntityDeletionOrUpdateAdapter<ModelNote>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `notes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ModelNote value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insert(final ModelNote modelNote) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfModelNote.insert(modelNote);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ModelNote modelNote) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfModelNote.handle(modelNote);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ModelNote> getAllNote() {
    final String _sql = "SELECT * FROM notes ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "date_time");
      final int _cursorIndexOfSubTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "sub_title");
      final int _cursorIndexOfNoteText = CursorUtil.getColumnIndexOrThrow(_cursor, "note_text");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "image_path");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "web_url");
      final List<ModelNote> _result = new ArrayList<ModelNote>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ModelNote _item;
        _item = new ModelNote();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpDateTime;
        _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
        _item.setDateTime(_tmpDateTime);
        final String _tmpSubTitle;
        _tmpSubTitle = _cursor.getString(_cursorIndexOfSubTitle);
        _item.setSubTitle(_tmpSubTitle);
        final String _tmpNoteText;
        _tmpNoteText = _cursor.getString(_cursorIndexOfNoteText);
        _item.setNoteText(_tmpNoteText);
        final String _tmpImagePath;
        _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        _item.setImagePath(_tmpImagePath);
        final String _tmpColor;
        _tmpColor = _cursor.getString(_cursorIndexOfColor);
        _item.setColor(_tmpColor);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        _item.setUrl(_tmpUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
