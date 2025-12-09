package com.example.lab9_1

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // 建立兩個數值，用於計算兔子與烏龜的進度
    private var progressRabbit = 0
    private var progressTurtle = 0
    // 建立變數以利後續綁定元件
    private lateinit var btnStart: Button
    private lateinit var sbRabbit: SeekBar
    private lateinit var sbTurtle: SeekBar
    // Coroutine jobs
    private var rabbitJob: Job? = null
    private var turtleJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 將變數與XML元件綁定
        btnStart = findViewById(R.id.btnStart)
        sbRabbit = findViewById(R.id.sbRabbit)
        sbTurtle = findViewById(R.id.sbTurtle)
        // 對開始按鈕設定監聽器
        btnStart.setOnClickListener {
            // 進行賽跑後按鈕不可被操作
            btnStart.isEnabled = false
            // 初始化兔子的賽跑進度
            progressRabbit = 0
            // 初始化烏龜的賽跑進度
            progressTurtle = 0
            // 初始化兔子的SeekBar進度
            sbRabbit.progress = 0
            // 初始化烏龜的SeekBar進度
            sbTurtle.progress = 0
            // 兔子起跑
            runRace()
        }
    }

    // 建立 showToast 方法顯示Toast訊息
    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


    private fun runRace() {
        rabbitJob?.cancel()
        turtleJob?.cancel()

        rabbitJob = lifecycleScope.launch {
            runRabbit()
        }
        turtleJob = lifecycleScope.launch {
            runTurtle()
        }
    }

    private suspend fun runRabbit() {
        // 兔子有三分之二的機率會偷懶
        val sleepProbability = arrayOf(true, true, false)
        while (progressRabbit < 100 && progressTurtle < 100) {
            delay(100) // 延遲0.1秒更新賽況
            if (sleepProbability.random())
                delay(300) // 兔子偷懶0.3秒
            progressRabbit += 3 // 每次跑三步
            sbRabbit.progress = progressRabbit

            if (progressRabbit >= 100 && progressTurtle < 100) {
                showToast("兔子勝利") // 顯示兔子勝利
                btnStart.isEnabled = true // 按鈕可操作
                turtleJob?.cancel()
            }
        }
    }

    private suspend fun runTurtle() {
        while (progressTurtle < 100 && progressRabbit < 100) {
            delay(100) // 延遲0.1秒更新賽況
            progressTurtle += 1 // 每次跑一步
            sbTurtle.progress = progressTurtle

            if (progressTurtle >= 100 && progressRabbit < 100) {
                showToast("烏龜勝利") // 顯示烏龜勝利
                btnStart.isEnabled = true // 按鈕可操作
                rabbitJob?.cancel()
            }
        }
    }
}