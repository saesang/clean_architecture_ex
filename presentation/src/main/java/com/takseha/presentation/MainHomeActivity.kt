package com.takseha.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.TotalInfoData
import com.takseha.presentation.databinding.ActivityMainHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainHomeBinding
    private val viewModel: MainHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setBinding()

        with(binding) {
            // 이름 입력 시 '확인' 버튼 활성화
            inputName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    btnSave.isEnabled = inputName.text.isNotEmpty()
                }
            })

            // '확인' 버튼 클릭 시 운세 정보와 '뒤로가기' 버튼 나타남
            btnSave.setOnClickListener {
                val username = inputName.text.toString()
                viewModel.getTotalInfoData(username)
            }

            // '뒤로가기' 버튼 클릭 시 화면 초기화
            btnBack.setOnClickListener {
                setInitialState()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.totalInfoData.collectLatest {
                handleState(it)
            }
        }
    }

    private fun handleState(state: BaseState<TotalInfoData>) {
        with(binding) {
            when (state) {
                is BaseState.Loading -> {
                    // 로딩 상태 처리
                }
                is BaseState.Success -> {
                    inputName.apply {
                        isEnabled = false
                        setText(state.data?.username)
                    }
                    textFortune.text = state.data?.content
                    textFortune.visibility = VISIBLE
                    btnSave.visibility = GONE
                    btnBack.visibility = VISIBLE
                }
                is BaseState.Error -> {
                    // 에러 상태 처리
                }
                is BaseState.None -> {
                    setInitialState()
                }
            }
        }
    }

    private fun setInitialState() {
        with(binding) {
            inputName.apply {
                isEnabled = true
                setText("")
            }
            textFortune.visibility = GONE
            btnSave.visibility = VISIBLE
            btnBack.visibility = GONE
        }
    }

    private fun setBinding() {
        binding = ActivityMainHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}