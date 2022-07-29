package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimb_id;
	private int reimb_amount;
	private Timestamp reimb_submitted;
	private int reimb_author;
	private int reimb_status_id;
	private int reimb_type_id;
	
	private User author;
	private ReimbursementStatus reimb_status;
	private ReimbursementType reimb_type;
	
	public Reimbursement(int reimb_id, int reimb_amount, Timestamp reimb_submitted, int reimb_author,
			int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = reimb_author;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
	public Reimbursement(int reimb_amount, Timestamp reimb_submitted, int reimb_author, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = reimb_author;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
	
	public Reimbursement(int reimb_amount, int reimb_author, int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_author = reimb_author;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public int getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}
	public int getReimb_author() {
		return reimb_author;
	}
	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}
	public int getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}
	public int getReimb_type_id() {
		return reimb_type_id;
	}
	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public ReimbursementStatus getReimb_status() {
		return reimb_status;
	}
	public void setReimb_status(ReimbursementStatus reimb_status) {
		this.reimb_status = reimb_status;
	}
	public ReimbursementType getReimb_type() {
		return reimb_type;
	}
	public void setReimb_type(ReimbursementType reimb_type) {
		this.reimb_type = reimb_type;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_author=" + reimb_author + ", reimb_status_id=" + reimb_status_id
				+ ", reimb_type_id=" + reimb_type_id + ", author=" + author + ", reimb_status=" + reimb_status
				+ ", reimb_type=" + reimb_type + "]";
	}
	
	
}
