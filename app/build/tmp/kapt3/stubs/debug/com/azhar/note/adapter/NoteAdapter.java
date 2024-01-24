package com.azhar.note.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\f\u001a\u00020\nH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/azhar/note/adapter/NoteAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/azhar/note/adapter/NoteAdapter$NoteViewHolder;", "modelNoteListFilter", "", "Lcom/azhar/note/model/ModelNote;", "onClickItem", "Lcom/azhar/note/utils/onClickItemListener;", "(Ljava/util/List;Lcom/azhar/note/utils/onClickItemListener;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "NoteViewHolder", "app_debug"})
public final class NoteAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.azhar.note.adapter.NoteAdapter.NoteViewHolder> {
    private final java.util.List<com.azhar.note.model.ModelNote> modelNoteListFilter = null;
    private final com.azhar.note.utils.onClickItemListener onClickItem = null;
    
    public NoteAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.azhar.note.model.ModelNote> modelNoteListFilter, @org.jetbrains.annotations.NotNull
    com.azhar.note.utils.onClickItemListener onClickItem) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.azhar.note.adapter.NoteAdapter.NoteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.azhar.note.adapter.NoteAdapter.NoteViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public int getItemViewType(int position) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016\u00a8\u0006\u001d"}, d2 = {"Lcom/azhar/note/adapter/NoteAdapter$NoteViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/azhar/note/adapter/NoteAdapter;Landroid/view/View;)V", "cvNote", "Landroidx/cardview/widget/CardView;", "getCvNote", "()Landroidx/cardview/widget/CardView;", "setCvNote", "(Landroidx/cardview/widget/CardView;)V", "roundedImageView", "Lcom/makeramen/roundedimageview/RoundedImageView;", "getRoundedImageView", "()Lcom/makeramen/roundedimageview/RoundedImageView;", "setRoundedImageView", "(Lcom/makeramen/roundedimageview/RoundedImageView;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "timeDate", "getTimeDate", "setTimeDate", "title", "getTitle", "setTitle", "app_debug"})
    public final class NoteViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView title;
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView text;
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView timeDate;
        @org.jetbrains.annotations.NotNull
        private androidx.cardview.widget.CardView cvNote;
        @org.jetbrains.annotations.NotNull
        private com.makeramen.roundedimageview.RoundedImageView roundedImageView;
        
        public NoteViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTitle() {
            return null;
        }
        
        public final void setTitle(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getText() {
            return null;
        }
        
        public final void setText(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTimeDate() {
            return null;
        }
        
        public final void setTimeDate(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.cardview.widget.CardView getCvNote() {
            return null;
        }
        
        public final void setCvNote(@org.jetbrains.annotations.NotNull
        androidx.cardview.widget.CardView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.makeramen.roundedimageview.RoundedImageView getRoundedImageView() {
            return null;
        }
        
        public final void setRoundedImageView(@org.jetbrains.annotations.NotNull
        com.makeramen.roundedimageview.RoundedImageView p0) {
        }
    }
}