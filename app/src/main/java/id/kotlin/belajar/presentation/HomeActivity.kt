package id.kotlin.belajar.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.android.support.DaggerAppCompatActivity
import id.kotlin.belajar.R
import id.kotlin.belajar.databinding.ActivityHomeBinding
import javax.inject.Inject



class HomeActivity : DaggerAppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityHomeBinding


    private val viewModel: HomeView by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        viewModel.states.observe(this, Observer { state ->
            when (state){
                is HomeViewState.Loading -> binding.pbHome.visibility = View.VISIBLE
                is HomeViewState.Success -> {
                    binding.pbHome.visibility = View.GONE
                    binding.rvHome.addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
                    binding.rvHome.adapter = HomeAdapter(state.response.results)
                }
                is HomeViewState.Error -> {
                    binding.pbHome.visibility = View.GONE
                    Log.e(HomeActivity::class.java.simpleName, "${state.error}")
                }
            }
        })
        viewModel.discoverMovie()
    }
}