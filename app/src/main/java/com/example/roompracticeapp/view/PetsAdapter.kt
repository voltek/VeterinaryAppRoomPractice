package com.example.roompracticeapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roompracticeapp.R
import com.example.roompracticeapp.databinding.PetItemLayoutBinding
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity
import com.example.roompracticeapp.utils.value

class PetsAdapter(
    var petsList: MutableList<PetEntity> = mutableListOf(),
    var petClickListener: PetClickListener
) :
    RecyclerView.Adapter<PetsAdapter.PetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item_layout, parent, false)
        return PetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = petsList.size

    fun addPet(petEntity: PetEntity) {

        petsList.add(petEntity)
        notifyItemInserted(petsList.size)
    }

    fun addPets(dogsList: MutableList<PetEntity>) {
        this.petsList.clear()
        this.petsList = dogsList
        notifyDataSetChanged()
    }

    inner class PetViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val itemViewBinding = PetItemLayoutBinding.bind(itemView)
        private val resources = itemViewBinding.tvItemPetName.context.resources

        fun bind(position: Int) {
            with(petsList[position]) {
                itemViewBinding.tvItemPetName.text = name
                itemViewBinding.tvItemPetRace.text = race
                itemViewBinding.tvItemPetAge.text = resources.getQuantityString(
                    R.plurals.dog_age_plural,
                    age.value(),
                    age.value()
                )

                itemViewBinding.cvPetItem.setOnClickListener {
                    petClickListener.onPetClicked(petsList[position])
                }
            }
        }
    }

    interface PetClickListener {
        fun onPetClicked(pet: PetEntity)
    }
}