package com.exercise7.core.dao;

public interface DAOInterface <Type> {
	default void add(Type added) {
		System.out.println("TEST");
	}
//	public static void delete(Type deleted);
//	public static void update(Type updated);
//	public static Type get(Long id);
}