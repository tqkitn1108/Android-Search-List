package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchlist.adapter.StudentAdapter
import com.example.searchlist.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        recyclerView = findViewById(R.id.recyclerView)
        etSearch = findViewById(R.id.etSearch)

        studentList = listOf(
            Student("Nguyen Van A", "20210101"),
            Student("Tran Thi B", "20214566"),
            Student("Vu Tuan Hung", "20210105"),
            Student("Nguyen Duc Khoa", "20214546"),
            Student("Ta Quang Kien", "20210502"),
            Student("Pham Dang Anh Duc", "20210207"),
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                if (searchText.length >= 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(searchText, ignoreCase = true) ||
                                it.mssv.contains(searchText)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}