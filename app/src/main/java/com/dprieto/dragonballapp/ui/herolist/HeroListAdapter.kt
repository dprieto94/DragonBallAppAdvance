package com.dprieto.dragonballapp.ui.herolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dprieto.dragonballapp.R
import com.dprieto.dragonballapp.databinding.ItemListHeroBinding
import com.dprieto.dragonballapp.domain.HeroModel

class HeroListAdapter (private val itemClickListener: (HeroModel) -> (Unit)):
    ListAdapter<HeroModel,
            HeroListAdapter.HeroViewHolder>(HeroItemDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_hero, parent, false)

        val binding = ItemListHeroBinding.bind(view)
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class HeroViewHolder(private val binding: ItemListHeroBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var hero: HeroModel
        init{
            binding.root.setOnClickListener {
                itemClickListener(hero)
            }
        }

        fun bind(hero: HeroModel){
            this.hero = hero

            with(binding){
                heroName.text = hero.name
                heroFavorite.isChecked = hero.favorite
                heroImage.load(hero.photo)
            }
        }
    }

    class HeroItemDiffCallback: DiffUtil.ItemCallback<HeroModel>() {
        override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem == newItem
        }

    }
}
