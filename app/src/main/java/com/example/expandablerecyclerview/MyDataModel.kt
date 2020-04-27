package com.example.expandablerecyclerview

data class MyDataModel(var isExpanded:Boolean,val tag:String,val innerList: ArrayList<InnerDataModel>?=null)