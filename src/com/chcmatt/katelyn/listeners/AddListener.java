package com.chcmatt.katelyn.listeners;

public @interface AddListener
{
	/** Set to false to disable this listener*/
	boolean value() default true;
}
