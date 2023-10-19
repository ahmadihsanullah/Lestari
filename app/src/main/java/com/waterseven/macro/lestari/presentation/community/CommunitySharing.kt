package com.waterseven.macro.lestari.presentation.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.waterseven.macro.lestari.data.community.CommunityData
import com.waterseven.macro.lestari.databinding.FragmentCommunitySharingBinding
import com.waterseven.macro.lestari.model.community.Community
import com.waterseven.macro.lestari.presentation.community.adapter.RvCommunitySharingAdapter


class CommunitySharing : Fragment() {

    private lateinit var binding: FragmentCommunitySharingBinding
    private lateinit var communitySharingAdapter: RvCommunitySharingAdapter
    private val communityData = CommunityData.dummyCommunity
    private val communityHasJoin : MutableList<Community> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunitySharingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView(){
        communityData.forEach{
            if(!it.join){
                communityHasJoin.add(it)
            }
        }
        communitySharingAdapter = RvCommunitySharingAdapter { community ->
            val data = CommunityFragmentDirections.actionCommunityFragmentToCommunityDetailFragment(
                community
            )
            findNavController().navigate(data)
        }

        binding.rvCommunitySharing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = communitySharingAdapter
        }

        communitySharingAdapter.submitList(communityHasJoin)
    }
}