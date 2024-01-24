package com.azhar.note.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.text.Editable
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.azhar.modelNote.R
import com.azhar.note.database.NoteDatabase
import com.azhar.note.model.ModelNote
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.layout_delete.*
import kotlinx.android.synthetic.main.layout_url.*
import kotlinx.android.synthetic.main.layout_url.view.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.AlertDialog
import android.graphics.Bitmap
import android.media.MediaRecorder
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class CreateNoteActivity : AppCompatActivity() {

    private var alertDialog: AlertDialog? = null
    private var selectImagePath: String? = null
    private var modelNoteExtra: ModelNote? = null
    private val ID_BahasaIndonesia = "id"
    private val RESULT_SPEECH = 1
    private val PERMISSION_CODE = 100
    private val GALLERY_REQUEST_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    private var voiceText = "" // Variable to store voice-to-text content
    private lateinit var editTextDesc: EditText
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var imageView: ImageView

    @SuppressLint("SetTextI18n", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        //Speech to text
        btnAddVoice.setOnClickListener {
            val mic_google = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            mic_google.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            mic_google.putExtra(RecognizerIntent.EXTRA_LANGUAGE, ID_BahasaIndonesia)

            try {
                startActivityForResult(mic_google, RESULT_SPEECH)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Maaf, Device Kamu Tidak Support Speech To Text", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }



        editTextDesc = findViewById(R.id.editTextDesc)
        imageView = findViewById(R.id.imageNote)

        //camera

        // bisa bicara text to speak
        textToSpeech = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                val indoLocale = Locale("id", "ID")
                textToSpeech.language = indoLocale
            }
        }

        speakButton.setOnClickListener {
            val text = editTextDesc.text.toString()
            if (text.isNotEmpty()) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }



        //EEEE, dd MMMM yyyy HH:mm a
        //Hari, Tanggal bulan tahun, jam a = malam m = pagi
        tvDateTime.setText("Terakhir diubah : " + SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date()))

        //image Path
        selectImagePath = ""

        if (intent.getBooleanExtra("EXTRA", false)) {
            modelNoteExtra = intent.getSerializableExtra("EXTRA_NOTE") as ModelNote
            setViewOrUpdateNote()
        }

        if (modelNoteExtra != null) {
            linearDelete.visibility = View.VISIBLE
            btnDelete.setOnClickListener {
                showDeleteDialog()
            }
        }

        btnHapusUrl.setOnClickListener {
            tvUrlNote.setText(null)
            tvUrlNote.setVisibility(View.GONE)
            btnHapusUrl.setVisibility(View.GONE)
        }

        btnAddUrl.setOnClickListener {
            showDialogUrl()
        }

        btnAddImage.setOnClickListener {
            showImageChooser()
        }

        fabDeleteImage.setOnClickListener {
            imageNote.setImageBitmap(null)
            imageNote.setVisibility(View.GONE)
            fabDeleteImage.setVisibility(View.GONE)
            selectImagePath = ""
        }

        fabSaveNote.setOnClickListener(View.OnClickListener {
            if (editTextTitle.getText().toString().isEmpty()) {
                Toast.makeText(this@CreateNoteActivity, "Judul Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
//            else if (editTextSubTitle.getText().toString().isEmpty() && editTextDesc.getText().toString().isEmpty()) {
//                Toast.makeText(this@CreateNoteActivity, "Catatan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
//                return@OnClickListener
//            }

            val modelNote = ModelNote()
            modelNote.title = editTextTitle.getText().toString()
           // modelNote.subTitle = editTextSubTitle.getText().toString()
            modelNote.noteText = editTextDesc.getText().toString()
            modelNote.dateTime = tvDateTime.getText().toString()
            modelNote.imagePath = selectImagePath

            if (tvUrlNote.getVisibility() == View.VISIBLE) {
                modelNote.url = tvUrlNote.getText().toString()
                btnHapusUrl.visibility = View.VISIBLE
            }

            if (modelNoteExtra != null) {
                modelNote.id = modelNoteExtra!!.id
            }

            class saveNoteAsyncTask : AsyncTask<Void?, Void?, Void?>() {
                override fun doInBackground(vararg p0: Void?): Void? {
                    NoteDatabase.getInstance(applicationContext)?.noteDao()?.insert(modelNote)
                    return null
                }

                override fun onPostExecute(aVoid: Void?) {
                    super.onPostExecute(aVoid)
                    val intent = Intent()
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            saveNoteAsyncTask().execute()
        })
    }


    override fun onDestroy() {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
        super.onDestroy()
    }
    private fun showImageChooser() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                    PERMISSION_CODE
                )
                return
            }
        }
        openImageChooser()
    }

    private fun openImageChooser() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Add Image")
        builder.setItems(options) { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                }
                options[item] == "Choose from Gallery" -> {
                    val galleryIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
                }
                options[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }


    @SuppressLint("RestrictedApi")
    private fun setViewOrUpdateNote() {
        editTextTitle.setText(modelNoteExtra?.title)
        //editTextSubTitle.setText(modelNoteExtra?.subTitle)
        editTextDesc.setText(modelNoteExtra?.noteText)

        if (modelNoteExtra?.imagePath != null && !modelNoteExtra?.imagePath?.trim().isNullOrEmpty()) {
            imageNote.setImageBitmap(BitmapFactory.decodeFile(modelNoteExtra?.imagePath))
            imageNote.visibility = View.VISIBLE
            selectImagePath = modelNoteExtra?.imagePath
            // Menyesuaikan skala ImageView dengan ukuran gambar asli
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            imageView.adjustViewBounds = true

            fabDeleteImage.visibility = View.VISIBLE
        }

        if (modelNoteExtra?.url != null && !modelNoteExtra?.url?.trim().isNullOrEmpty()) {
            tvUrlNote.text = modelNoteExtra?.url
            tvUrlNote.visibility = View.VISIBLE
            btnHapusUrl.visibility = View.VISIBLE
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageChooser()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    if (data != null) {
                        val selectImgUri = data.data
                        if (selectImgUri != null) {
                            try {
                                val inputStream = contentResolver.openInputStream(selectImgUri)
                                val bitmap = BitmapFactory.decodeStream(inputStream)
                                imageView.setImageBitmap(bitmap)
                                imageView.visibility = View.VISIBLE
                                selectImagePath = getPathFromUri(selectImgUri)
                                fabDeleteImage.visibility = View.VISIBLE
                                // Do whatever you need with the selected image here
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                CAMERA_REQUEST_CODE -> {
                    if (data != null) {
                        val selectImgUri = data.data
                        if (selectImgUri != null) {
                            // Menampilkan gambar dari URI menggunakan Glide
                            Glide.with(this)
                                .load(selectImgUri)
                                .into(imageView)

                            imageView.visibility = View.VISIBLE
                            selectImagePath = getPathFromUri(selectImgUri)
                            fabDeleteImage.visibility = View.VISIBLE

                            // Menyesuaikan skala ImageView dengan ukuran gambar asli
                            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                            imageView.adjustViewBounds = true

                            // Save original image URI for further use
                            val originalImageUri = selectImgUri

                            // Save image to the gallery
                            saveImageToGallery(originalImageUri)
                        } else {
                            val imageBitmap = data.extras?.get("data") as Bitmap
                            imageView.setImageBitmap(imageBitmap)
                            imageView.visibility = View.VISIBLE
                            selectImagePath = getPathFromBitmap(imageBitmap)

                            // Menyesuaikan skala ImageView dengan ukuran gambar asli
                            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                            imageView.adjustViewBounds = true
                            fabDeleteImage.visibility = View.VISIBLE

                            // Save image to the gallery
                            saveBitmapToGallery(imageBitmap)
                        }
                    }
                }



            }
        }
        when (requestCode) {
            RESULT_SPEECH -> {
                if (resultCode == RESULT_OK && data != null) {
                    val text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (text != null && text.isNotEmpty()) {
                        val newText = text[0]
                        voiceText += " $newText" // Append new voice-to-text result to existing voiceText
                        val editTextText = editTextDesc.text.toString() // Get current text in editTextDesc
                        val finalText = "$editTextText $newText" // Combine manual and voice text
                        val editableText = Editable.Factory.getInstance().newEditable(finalText)
                        editTextDesc.text = editableText
                    }
                }
            }
        }
    }
    private fun saveImageToGallery(imageUri: Uri) {
        val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
        val imageBitmap = BitmapFactory.decodeStream(imageStream)
        saveBitmapToGallery(imageBitmap)
    }

    private fun saveBitmapToGallery(bitmap: Bitmap) {
        val filename = "Image_" + System.currentTimeMillis() / 1000 + ".jpg"
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        folder.mkdirs()

        val file = File(folder, filename)
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // Refresh the gallery to show the newly saved image
            MediaScannerConnection.scanFile(this, arrayOf(file.absolutePath), null) { _, _ -> }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun getPathFromBitmap(bitmap: Bitmap): String {
        val filesDir = applicationContext.filesDir
        val imageFile = File(filesDir, "Image_" + System.currentTimeMillis() / 1000 + ".jpg")

        try {
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return imageFile.absolutePath
    }

    private fun getPathFromUri(contentUri: Uri): String? {
        val filePath: String?
        val cursor = contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }

    private fun showDeleteDialog() {
        val dialog = Dialog(this@CreateNoteActivity)
        dialog.setContentView(R.layout.layout_delete)
        dialog.tvHapusCatatan.setOnClickListener {

            class HapusNoteAsyncTask : AsyncTask<Void, Void, Void>() {

                override fun doInBackground(vararg p0: Void?): Void? {
                    // Lakukan pekerjaan di latar belakang di sini
                    return null
                }

                override fun onPostExecute(aVoid: Void?) {
                    super.onPostExecute(aVoid)
                    val intent = Intent()
                    intent.putExtra("NoteDelete", true)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            HapusNoteAsyncTask().execute()
        }
        dialog.tvBatalHapus.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogUrl() {
        if (alertDialog == null) {
            val builder = AlertDialog.Builder(this@CreateNoteActivity)
            val view = LayoutInflater.from(this).inflate(R.layout.layout_url, findViewById(R.id.layoutUrl) as? ViewGroup)
            builder.setView(view)

            alertDialog = builder.create()
            if (alertDialog?.window != null) {
                alertDialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            }

            val etUrl = view.editTextAddUrl
            etUrl.requestFocus()

            view.tvOk.setOnClickListener {
                if (etUrl.text.toString().trim().isEmpty()) {
                    Toast.makeText(this@CreateNoteActivity, "Masukan Url", Toast.LENGTH_SHORT).show()
                } else if (!Patterns.WEB_URL.matcher(etUrl.text.toString()).matches()) {
                    Toast.makeText(this@CreateNoteActivity, "Url Anda Tidak Benar", Toast.LENGTH_SHORT).show()
                } else {
                    tvUrlNote.text = etUrl.text.toString()
                    tvUrlNote.visibility = View.VISIBLE
                    btnHapusUrl.visibility = View.VISIBLE
                    alertDialog?.dismiss()
                }
            }

            view.tvBatal.setOnClickListener {
                alertDialog?.dismiss()
            }
        }
        alertDialog?.show()
    }

    companion object {
        private const val REQUEST_PERMISSION = 1
        private const val REQUEST_SELECT = 2
    }



}
