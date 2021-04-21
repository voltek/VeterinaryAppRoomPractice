package com.example.roompracticeapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roompracticeapp.databinding.ActivityVeterinaryBinding
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity
import com.example.roompracticeapp.utils.value
import com.example.roompracticeapp.viewmodel.VeterinaryViewModel

class VeterinaryActivity : AppCompatActivity(), PetsAdapter.PetClickListener {

    private val veterinaryViewModel: VeterinaryViewModel by lazy {
        val vmFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ViewModelProvider(this, vmFactory).get(VeterinaryViewModel::class.java)
    }

    private val viewBinding: ActivityVeterinaryBinding by lazy {
        ActivityVeterinaryBinding.inflate(layoutInflater)
    }
    private val petsAdapter: PetsAdapter = PetsAdapter(petClickListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        setRecyclerview()
        addObservers()
        setButtons()
    }

    override fun onPetClicked(pet: PetEntity) {
        Toast.makeText(this, "Pet selected: ${pet.name}", Toast.LENGTH_SHORT).show()
        veterinaryViewModel.petSelected = pet
    }

    private fun setRecyclerview() {
        viewBinding.rvVAPetsAdded.adapter = petsAdapter
    }

    private fun addObservers() {
        veterinaryViewModel.getPets().observe(this, { dogsList ->
            if (dogsList != null && dogsList.isNotEmpty()) {
                petsAdapter.addPets(dogsList.toMutableList())
                //dogsList.forEach { petsAdapter.addDog(it) }
            }
        })


    }

    private fun setButtons() {
        viewBinding.btnVAAdd.setOnClickListener { addPet() }
        viewBinding.btnVAUpdate.setOnClickListener { updatePet() }
        viewBinding.btnVADelete.setOnClickListener { deletePet() }
        viewBinding.btnVASearch.setOnClickListener {  }
    }

    private fun addPet() {
        val petName = viewBinding.tietVADogName.text.toString()
        val petRace = viewBinding.tietVADogRace.text.toString()
        val petAge = viewBinding.tietVADogAge.text.toString()

        if(veterinaryViewModel.areFieldsNotEmpty(petName,petRace,petAge)) {
            veterinaryViewModel.savePet(PetEntity(petName, petRace, petAge.toInt()))
        }
    }

    private fun updatePet() {
        val petName = viewBinding.tietVADogName.text.toString()
        val petRace = viewBinding.tietVADogRace.text.toString()
        val petAge = viewBinding.tietVADogAge.text.toString()

        if(veterinaryViewModel.areFieldsNotEmpty(petName,petRace,petAge)) {
            veterinaryViewModel.petSelected?.let {
                veterinaryViewModel.updatePet(
                    PetEntity(
                        petName, petRace, petAge.toInt(), veterinaryViewModel.petSelected?.uid.value()
                    )
                )
                veterinaryViewModel.resetPetSelected()
            }
        }
    }

    private fun deletePet() {
        veterinaryViewModel.petSelected?.let {
            veterinaryViewModel.deletePet(it)
            veterinaryViewModel.resetPetSelected()
        }
    }
}