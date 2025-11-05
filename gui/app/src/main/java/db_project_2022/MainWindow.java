/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package db_project_2022;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**

 @author Panagiotis
 */
public class MainWindow extends javax.swing.JFrame
{

	/**
	 Creates new form MainWindow
	 */
	public MainWindow()
	{
		//Netbeans stuff, DO NOT TOUCH
		initComponents();	
		
		//Main window stuff
		//=============================================================================================================
		
		//Title
		
		this.setTitle("Travel Agency");
		
		welcomeLabel.setText("Welcome, " + User.name() + "!");
		usernameJabel.setText(User.username());
		nameJabel.setText(User.name());
		lnameJabel.setText(User.lname());
		atJabel.setText(User.id());
		
		//Center the window
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos;
		int ypos;
		
		xpos = (int)((dimension.getWidth() - this.getWidth()) / 2);
		ypos = (int)((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(new java.awt.Point(xpos, ypos));
		
		xpos = (int)((dimension.getWidth() - profileDialog.getWidth()) / 2);
		ypos = (int)((dimension.getHeight() - profileDialog.getHeight()) / 2);
		
		profileDialog.setLocation(new java.awt.Point(xpos, ypos));
		editDialog.setLocation(new java.awt.Point(xpos, ypos));
		addItWorkerDialog.setLocation(new java.awt.Point(xpos, ypos));
		
		//Profile
		
		profileDialog.setTitle("User information");
		editDialog.setTitle("Edit");
		
		profileDialog.setModal(true);
		editDialog.setModal(true);
		
		errorDialog.setModal(true);
		addItWorkerDialog.setModal(true);
		
		//=============================================================================================================
		
		//Main Menu navigation
		//=============================================================================================================
		
		mainList.setSelectedIndex(0);
		currentPanel = panelOption0;
		
		panelOption1.setVisible(false);
		panelOption2.setVisible(false);
		panelOption3.setVisible(false);
		panelOption4.setVisible(false);
		panelOption5.setVisible(false);
		panelOption6.setVisible(false);
		
		editAdminPanel.setVisible(false);
		editBranchPanel.setVisible(false);
		editDestinationPanel.setVisible(false);
		editDriverPanel.setVisible(false);
		editEventPanel.setVisible(false);
		editGuidePanel.setVisible(false);
		editItWorkerPanel.setVisible(false);
		editLanguagesPanel.setVisible(false);
		editLogPanel.setVisible(false);
		editManagesPanel.setVisible(false);
		editOfferPanel.setVisible(false);
		editPhonesPanel.setVisible(false);
		editReservationPanel.setVisible(false);
		editReservationOfferPanel.setVisible(false);
		editTravelToPanel.setVisible(false);
		editWorkerPanel.setVisible(false);
		editTripPanel.setVisible(false);
		
		//=============================================================================================================
		
		//Table stuff
		//=============================================================================================================
		branchTable.getTableHeader().setReorderingAllowed(false);
		branchTable.getTableHeader().setResizingAllowed(false);
		branchTable.setEnabled(false);
		
		logTable.getTableHeader().setReorderingAllowed(false);
		logTable.getTableHeader().setResizingAllowed(false);
		logTable.setEnabled(false);
		
		mainTable.getTableHeader().setReorderingAllowed(false);
		mainTable.getTableHeader().setResizingAllowed(false);
		
		brinfoEmployeeTable.getTableHeader().setReorderingAllowed(false);
		brinfoEmployeeTable.getTableHeader().setResizingAllowed(false);
		brinfoEmployeeTable.setEnabled(false);
		
		brinfoManagerTable.getTableHeader().setReorderingAllowed(false);
		brinfoManagerTable.getTableHeader().setResizingAllowed(false);
		brinfoManagerTable.setEnabled(false);
		
		brinfoTable.getTableHeader().setReorderingAllowed(false);
		brinfoTable.getTableHeader().setResizingAllowed(false);
		brinfoTable.setEnabled(false);
		
		profitTable.getTableHeader().setReorderingAllowed(false);
		profitTable.getTableHeader().setResizingAllowed(false);
		profitTable.setEnabled(false);
		
		expensesTable.getTableHeader().setReorderingAllowed(false);
		expensesTable.getTableHeader().setResizingAllowed(false);
		expensesTable.setEnabled(false);
		
		addWorkerTable.getTableHeader().setReorderingAllowed(false);
		addWorkerTable.getTableHeader().setResizingAllowed(false);
		
		addItTable.getTableHeader().setReorderingAllowed(false);
		addItTable.getTableHeader().setResizingAllowed(false);
		
		resoffTable.getTableHeader().setReorderingAllowed(false);
		resoffTable.getTableHeader().setResizingAllowed(false);
		resoffTable.setEnabled(false);
		
		//Because rows and columns of the main table are dynamic, I cannot only have some cells to be non-editable
		//I need isCellEditable to always return false: never allow a cell to be edited
		//However Netbeans does not allow me to edit its code in initComponents()
		//So I reset the table model myself
	
		setModel(mainTable);
		setModel(branchTable);
		setModel(logTable);
		setModel(addWorkerTable);
		setModel(addItTable);
		setModel(profitTable);
		setModel(expensesTable);
		setModel(resoffTable);
		
		//=============================================================================================================
		
		//Initialize datetime fields
		//=============================================================================================================
		
		//Main
		
		datetime_field_ev_start = new DatetimeFields
		(
				1,
				field_ev_start_year,
				field_ev_start_month,
				field_ev_start_day,
				field_ev_start_hours,
				field_ev_start_minutes,
				field_ev_start_seconds
		);

		datetime_field_ev_end = new DatetimeFields
		(
				2,
				field_ev_end_year,
				field_ev_end_month,
				field_ev_end_day,
				field_ev_end_hours,
				field_ev_end_minutes,
				field_ev_end_seconds
		);

		datetime_field_tr_departure = new DatetimeFields
		(
				1,
				field_tr_departure_year,
				field_tr_departure_month,
				field_tr_departure_day,
				field_tr_departure_hours,
				field_tr_departure_minutes,
				field_tr_departure_seconds
		);

		datetime_field_tr_return = new DatetimeFields
		(
				2,
				field_tr_return_year,
				field_tr_return_month,
				field_tr_return_day,
				field_tr_return_hours,
				field_tr_return_minutes,
				field_tr_return_seconds
		);

		datetime_field_to_arrival = new DatetimeFields
		(
				2,
				field_to_arrival_year,
				field_to_arrival_month,
				field_to_arrival_day,
				field_to_arrival_hours,
				field_to_arrival_minutes,
				field_to_arrival_seconds
		);

		datetime_field_to_departure = new DatetimeFields
		(
				3,
				field_to_departure_year,
				field_to_departure_month,
				field_to_departure_day,
				field_to_departure_hours,
				field_to_departure_minutes,
				field_to_departure_seconds
		);

		datetime_field_log_time = new DatetimeFields
		(
				4,
				field_log_time_year,
				field_log_time_month,
				field_log_time_day,
				field_log_time_hours,
				field_log_time_minutes,
				field_log_time_seconds
		);

		datetime_field_it_start_date = new DatetimeFields
		(
				3,
				field_it_start_date_year,
				field_it_start_date_month,
				field_it_start_date_day,
				field_it_start_date_hours,
				field_it_start_date_minutes,
				field_it_start_date_seconds
		);

		datetime_field_it_end_date = new DatetimeFields
		(
				4,
				field_it_end_date_year,
				field_it_end_date_month,
				field_it_end_date_day,
				field_it_end_date_hours,
				field_it_end_date_minutes,
				field_it_end_date_seconds
		);

		datetime_field_off_start = new DatetimeFields
		(
				2,
				field_off_start_year,
				field_off_start_month,
				field_off_start_day,
				field_off_start_hours,
				field_off_start_minutes,
				field_off_start_seconds
		);

		datetime_field_off_end = new DatetimeFields
		(
				3,
				field_off_end_year,
				field_off_end_month,
				field_off_end_day,
				field_off_end_hours,
				field_off_end_minutes,
				field_off_end_seconds
		);
		
		//Branch
		datetime_branch_start = new DatetimeFields
		(
				-1,
				branch_start_date_year,
				branch_start_date_month,
				branch_start_date_day,
				branch_start_date_hours,
				branch_start_date_minutes,
				branch_start_date_seconds
		);
		
		datetime_branch_end = new DatetimeFields
		(
				-1,
				branch_end_date_year,
				branch_end_date_month,
				branch_end_date_day,
				branch_end_date_hours,
				branch_end_date_minutes,
				branch_end_date_seconds
		);
		
		datetime_add_it = new DatetimeFields
		(
			-1,
			add_it_year,
			add_it_month,
			add_it_day,
			add_it_hours,
			add_it_minutes,
			add_it_seconds
		);
		
		//=============================================================================================================
		
		currentTable = -1;
		rowCountJabel.setText("Number of table entries: 0");
		emptyTable(mainTable, tableDefaultRowCount, tableDefaultColumnCount, tableWidth);
		setupTables();
	}

	/**
	 This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        profileDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usernameJabel = new javax.swing.JLabel();
        nameJabel = new javax.swing.JLabel();
        lnameJabel = new javax.swing.JLabel();
        atJabel = new javax.swing.JLabel();
        logoutDialog = new javax.swing.JDialog();
        logoutOptionPane = new javax.swing.JOptionPane();
        rowOperationDialog = new javax.swing.JDialog();
        rowOperationOptionPane = new javax.swing.JOptionPane();
        emptyLogDialog = new javax.swing.JDialog();
        emptyLogOptionPane = new javax.swing.JOptionPane();
        editDialog = new javax.swing.JDialog();
        editJabel = new javax.swing.JLabel();
        saveChangesButton = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        editAdminPanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        field_adm_AT = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        field_adm_type = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        field_adm_diploma = new javax.swing.JTextField();
        editBranchPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        field_br_code = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        field_br_city = new javax.swing.JTextField();
        field_br_street = new javax.swing.JTextField();
        field_br_num = new javax.swing.JTextField();
        editDestinationPanel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        field_dst_id = new javax.swing.JTextField();
        field_dst_name = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        field_dst_descr = new javax.swing.JTextArea();
        field_dst_language = new javax.swing.JComboBox<>();
        field_dst_rtype = new javax.swing.JComboBox<>();
        field_dst_location = new javax.swing.JComboBox<>();
        editDriverPanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        field_drv_route = new javax.swing.JComboBox<>();
        field_drv_license = new javax.swing.JComboBox<>();
        field_drv_experience = new javax.swing.JTextField();
        field_drv_AT = new javax.swing.JComboBox<>();
        editEventPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        field_ev_end_day = new javax.swing.JComboBox<>();
        field_ev_end_month = new javax.swing.JComboBox<>();
        jLabel123 = new javax.swing.JLabel();
        field_ev_start_year = new javax.swing.JComboBox<>();
        jLabel124 = new javax.swing.JLabel();
        field_ev_end_minutes = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        field_ev_end_year = new javax.swing.JComboBox<>();
        field_ev_start_hours = new javax.swing.JComboBox<>();
        jLabel126 = new javax.swing.JLabel();
        field_ev_start_day = new javax.swing.JComboBox<>();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        field_ev_end_hours = new javax.swing.JComboBox<>();
        jLabel130 = new javax.swing.JLabel();
        field_ev_start_month = new javax.swing.JComboBox<>();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        field_ev_start_minutes = new javax.swing.JComboBox<>();
        field_ev_start_seconds = new javax.swing.JComboBox<>();
        field_ev_end_seconds = new javax.swing.JComboBox<>();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        field_ev_descr = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        field_ev_tr_id = new javax.swing.JComboBox<>();
        editGuidePanel = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        field_gui_cv = new javax.swing.JTextArea();
        field_gui_AT = new javax.swing.JComboBox<>();
        editItWorkerPanel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        field_it_end_date_day = new javax.swing.JComboBox<>();
        field_it_end_date_month = new javax.swing.JComboBox<>();
        jLabel183 = new javax.swing.JLabel();
        field_it_start_date_year = new javax.swing.JComboBox<>();
        jLabel184 = new javax.swing.JLabel();
        field_it_end_date_minutes = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        field_it_end_date_year = new javax.swing.JComboBox<>();
        field_it_start_date_hours = new javax.swing.JComboBox<>();
        jLabel186 = new javax.swing.JLabel();
        field_it_start_date_day = new javax.swing.JComboBox<>();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        field_it_end_date_hours = new javax.swing.JComboBox<>();
        jLabel190 = new javax.swing.JLabel();
        field_it_start_date_month = new javax.swing.JComboBox<>();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        field_it_start_date_minutes = new javax.swing.JComboBox<>();
        field_it_start_date_seconds = new javax.swing.JComboBox<>();
        field_it_end_date_seconds = new javax.swing.JComboBox<>();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        field_it_username = new javax.swing.JTextField();
        field_it_password = new javax.swing.JTextField();
        field_it_AT = new javax.swing.JComboBox<>();
        editLanguagesPanel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        field_lng_gui_AT = new javax.swing.JComboBox<>();
        field_lng_language = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        editLogPanel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel201 = new javax.swing.JLabel();
        field_log_time_year = new javax.swing.JComboBox<>();
        jLabel204 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        field_log_time_hours = new javax.swing.JComboBox<>();
        jLabel206 = new javax.swing.JLabel();
        field_log_time_day = new javax.swing.JComboBox<>();
        jLabel207 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        field_log_time_month = new javax.swing.JComboBox<>();
        jLabel214 = new javax.swing.JLabel();
        field_log_time_minutes = new javax.swing.JComboBox<>();
        field_log_time_seconds = new javax.swing.JComboBox<>();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        field_log_entry_id = new javax.swing.JTextField();
        field_log_it_AT = new javax.swing.JComboBox<>();
        field_log_table = new javax.swing.JComboBox<>();
        field_log_action = new javax.swing.JComboBox<>();
        editManagesPanel = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        field_mng_adm_AT = new javax.swing.JComboBox<>();
        field_mng_br_code = new javax.swing.JComboBox<>();
        editOfferPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        field_off_end_day = new javax.swing.JComboBox<>();
        field_off_end_month = new javax.swing.JComboBox<>();
        jLabel163 = new javax.swing.JLabel();
        field_off_start_year = new javax.swing.JComboBox<>();
        jLabel164 = new javax.swing.JLabel();
        field_off_end_minutes = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        field_off_end_year = new javax.swing.JComboBox<>();
        field_off_start_hours = new javax.swing.JComboBox<>();
        jLabel166 = new javax.swing.JLabel();
        field_off_start_day = new javax.swing.JComboBox<>();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        field_off_end_hours = new javax.swing.JComboBox<>();
        jLabel170 = new javax.swing.JLabel();
        field_off_start_month = new javax.swing.JComboBox<>();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        field_off_start_minutes = new javax.swing.JComboBox<>();
        field_off_start_seconds = new javax.swing.JComboBox<>();
        field_off_end_seconds = new javax.swing.JComboBox<>();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        field_off_destination = new javax.swing.JComboBox<>();
        field_off_cost = new javax.swing.JTextField();
        field_off_id = new javax.swing.JTextField();
        editPhonesPanel = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        field_ph_number = new javax.swing.JTextField();
        field_ph_br_code = new javax.swing.JComboBox<>();
        editTripPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        field_tr_id = new javax.swing.JTextField();
        field_tr_maxseats = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        field_tr_cost = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        field_tr_br_code = new javax.swing.JComboBox<>();
        field_tr_gui_AT = new javax.swing.JComboBox<>();
        field_tr_drv_AT = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        field_tr_return_day = new javax.swing.JComboBox<>();
        field_tr_return_month = new javax.swing.JComboBox<>();
        jLabel103 = new javax.swing.JLabel();
        field_tr_departure_year = new javax.swing.JComboBox<>();
        jLabel104 = new javax.swing.JLabel();
        field_tr_return_minutes = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        field_tr_return_year = new javax.swing.JComboBox<>();
        field_tr_departure_hours = new javax.swing.JComboBox<>();
        jLabel106 = new javax.swing.JLabel();
        field_tr_departure_day = new javax.swing.JComboBox<>();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        field_tr_return_hours = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        field_tr_departure_month = new javax.swing.JComboBox<>();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        field_tr_departure_minutes = new javax.swing.JComboBox<>();
        field_tr_departure_seconds = new javax.swing.JComboBox<>();
        field_tr_return_seconds = new javax.swing.JComboBox<>();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        editReservationPanel = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        field_res_lname = new javax.swing.JTextField();
        field_res_tr_id = new javax.swing.JComboBox<>();
        field_res_isadult = new javax.swing.JComboBox<>();
        field_res_seatnum = new javax.swing.JTextField();
        field_res_name = new javax.swing.JTextField();
        editReservationOfferPanel = new javax.swing.JPanel();
        field_resoff_lname = new javax.swing.JTextField();
        field_resoff_off_id = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        field_resoff_id = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        field_resoff_name = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        field_resoff_deposit = new javax.swing.JTextField();
        editTravelToPanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        field_to_departure_day = new javax.swing.JComboBox<>();
        field_to_departure_month = new javax.swing.JComboBox<>();
        jLabel143 = new javax.swing.JLabel();
        field_to_arrival_year = new javax.swing.JComboBox<>();
        jLabel144 = new javax.swing.JLabel();
        field_to_departure_minutes = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        field_to_departure_year = new javax.swing.JComboBox<>();
        field_to_arrival_hours = new javax.swing.JComboBox<>();
        jLabel146 = new javax.swing.JLabel();
        field_to_arrival_day = new javax.swing.JComboBox<>();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        field_to_departure_hours = new javax.swing.JComboBox<>();
        jLabel150 = new javax.swing.JLabel();
        field_to_arrival_month = new javax.swing.JComboBox<>();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        field_to_arrival_minutes = new javax.swing.JComboBox<>();
        field_to_arrival_seconds = new javax.swing.JComboBox<>();
        field_to_departure_seconds = new javax.swing.JComboBox<>();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        field_to_tr_id = new javax.swing.JComboBox<>();
        field_to_dst_id = new javax.swing.JComboBox<>();
        editWorkerPanel = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        field_wrk_name = new javax.swing.JTextField();
        field_wrk_AT = new javax.swing.JTextField();
        field_wrk_salary = new javax.swing.JTextField();
        field_wrk_lname = new javax.swing.JTextField();
        field_wrk_br_code = new javax.swing.JComboBox<>();
        cancelChangesButton = new javax.swing.JButton();
        errorDialog = new javax.swing.JDialog();
        errorOptionPane = new javax.swing.JOptionPane();
        addItWorkerDialog = new javax.swing.JDialog();
        add_it_name = new javax.swing.JTextField();
        add_it_at = new javax.swing.JTextField();
        add_it_salary = new javax.swing.JTextField();
        add_it_lname = new javax.swing.JTextField();
        add_it_branch = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        add_it_username = new javax.swing.JTextField();
        add_it_password = new javax.swing.JTextField();
        editJabel1 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        add_it_hours = new javax.swing.JComboBox<>();
        jLabel223 = new javax.swing.JLabel();
        add_it_day = new javax.swing.JComboBox<>();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        add_it_month = new javax.swing.JComboBox<>();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        add_it_year = new javax.swing.JComboBox<>();
        add_it_minutes = new javax.swing.JComboBox<>();
        jLabel229 = new javax.swing.JLabel();
        add_it_seconds = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        saveChangesButton1 = new javax.swing.JButton();
        cancelChangesButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        welcomeLabel = new java.awt.Label();
        profileButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainList = new javax.swing.JList<>();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelOption0 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tableComboBox = new javax.swing.JComboBox<>();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        rowCountJabel = new javax.swing.JLabel();
        panelOption1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        branchTable = new javax.swing.JTable();
        branchComboBox = new javax.swing.JComboBox<>();
        branchButton = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel202 = new javax.swing.JLabel();
        branch_start_date_year = new javax.swing.JComboBox<>();
        jLabel208 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        branch_start_date_hours = new javax.swing.JComboBox<>();
        jLabel212 = new javax.swing.JLabel();
        branch_start_date_day = new javax.swing.JComboBox<>();
        jLabel213 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        branch_start_date_month = new javax.swing.JComboBox<>();
        jLabel219 = new javax.swing.JLabel();
        branch_start_date_minutes = new javax.swing.JComboBox<>();
        branch_start_date_seconds = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel211 = new javax.swing.JLabel();
        branch_end_date_year = new javax.swing.JComboBox<>();
        jLabel216 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        branch_end_date_hours = new javax.swing.JComboBox<>();
        jLabel220 = new javax.swing.JLabel();
        branch_end_date_day = new javax.swing.JComboBox<>();
        jLabel221 = new javax.swing.JLabel();
        jLabel281 = new javax.swing.JLabel();
        branch_end_date_month = new javax.swing.JComboBox<>();
        jLabel282 = new javax.swing.JLabel();
        branch_end_date_minutes = new javax.swing.JComboBox<>();
        branch_end_date_seconds = new javax.swing.JComboBox<>();
        branchButtonSelect = new javax.swing.JButton();
        branchButtonEmpty = new javax.swing.JButton();
        branchRowCountJabel = new javax.swing.JLabel();
        panelOption2 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        brinfoEmployeeTable = new javax.swing.JTable();
        jLabel86 = new javax.swing.JLabel();
        brinfoComboBox = new javax.swing.JComboBox<>();
        brinfoEmployeeJabel = new javax.swing.JLabel();
        brinfoSalarJabel = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        brinfoManagerTable = new javax.swing.JTable();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        brinfoTable = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        panelOption3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        expensesTable = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        profitTable = new javax.swing.JTable();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        profitComboBox = new javax.swing.JComboBox<>();
        jLabel96 = new javax.swing.JLabel();
        incomeJabel = new javax.swing.JLabel();
        expensesJabel = new javax.swing.JLabel();
        profitJabel = new javax.swing.JLabel();
        reservationJabel = new javax.swing.JLabel();
        expenseEmployeeJabel = new javax.swing.JLabel();
        panelOption4 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        resoffTable = new javax.swing.JTable();
        resoff_lname_field = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        resoff_run_button = new javax.swing.JButton();
        panelOption5 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        addWorkerTable = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        addItTable = new javax.swing.JTable();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        addNewItWorkerButton = new javax.swing.JButton();
        transferWorkerButton = new javax.swing.JButton();
        panelOption6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        logTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        logCountJabel = new javax.swing.JLabel();
        emptyLogButton = new javax.swing.JButton();

        profileDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        profileDialog.setAlwaysOnTop(true);
        profileDialog.setMinimumSize(new java.awt.Dimension(250, 390));
        profileDialog.setSize(new java.awt.Dimension(250, 390));

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/default.png"))); // NOI18N
        jLabel1.setToolTipText("That's you!");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Lastname: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Name: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("AT:");

        usernameJabel.setText("Username:");

        nameJabel.setText("Name: ");

        lnameJabel.setText("Lastname: ");

        atJabel.setText("AT:");

        javax.swing.GroupLayout profileDialogLayout = new javax.swing.GroupLayout(profileDialog.getContentPane());
        profileDialog.getContentPane().setLayout(profileDialogLayout);
        profileDialogLayout.setHorizontalGroup(
            profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileDialogLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profileDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lnameJabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameJabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(atJabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(profileDialogLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(usernameJabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(profileDialogLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        profileDialogLayout.setVerticalGroup(
            profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileDialogLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameJabel)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJabel)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnameJabel)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(profileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atJabel)
                    .addComponent(jLabel5))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        logoutOptionPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout logoutDialogLayout = new javax.swing.GroupLayout(logoutDialog.getContentPane());
        logoutDialog.getContentPane().setLayout(logoutDialogLayout);
        logoutDialogLayout.setHorizontalGroup(
            logoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        logoutDialogLayout.setVerticalGroup(
            logoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout rowOperationDialogLayout = new javax.swing.GroupLayout(rowOperationDialog.getContentPane());
        rowOperationDialog.getContentPane().setLayout(rowOperationDialogLayout);
        rowOperationDialogLayout.setHorizontalGroup(
            rowOperationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rowOperationOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        rowOperationDialogLayout.setVerticalGroup(
            rowOperationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rowOperationOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout emptyLogDialogLayout = new javax.swing.GroupLayout(emptyLogDialog.getContentPane());
        emptyLogDialog.getContentPane().setLayout(emptyLogDialogLayout);
        emptyLogDialogLayout.setHorizontalGroup(
            emptyLogDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emptyLogOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        emptyLogDialogLayout.setVerticalGroup(
            emptyLogDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emptyLogOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        editDialog.setAlwaysOnTop(true);
        editDialog.setMinimumSize(new java.awt.Dimension(500, 400));
        editDialog.setResizable(false);

        editJabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        editJabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editJabel.setText("Edit");
        editJabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveChangesButtonActionPerformed(evt);
            }
        });

        jLayeredPane2.setMaximumSize(new java.awt.Dimension(500, 268));
        jLayeredPane2.setMinimumSize(new java.awt.Dimension(500, 268));

        jLabel29.setText("AT:");

        field_adm_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setText("Admin Type:");

        field_adm_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOGISTICS", "ADMINISTRATIVE", "ACCOUNTING" }));

        jLabel31.setText("Diploma:");

        javax.swing.GroupLayout editAdminPanelLayout = new javax.swing.GroupLayout(editAdminPanel);
        editAdminPanel.setLayout(editAdminPanelLayout);
        editAdminPanelLayout.setHorizontalGroup(
            editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editAdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editAdminPanelLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_adm_AT, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editAdminPanelLayout.createSequentialGroup()
                        .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_adm_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_adm_diploma))))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        editAdminPanelLayout.setVerticalGroup(
            editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editAdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(field_adm_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(field_adm_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(field_adm_diploma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        editBranchPanel.setMaximumSize(new java.awt.Dimension(500, 268));
        editBranchPanel.setMinimumSize(new java.awt.Dimension(500, 268));
        editBranchPanel.setPreferredSize(new java.awt.Dimension(500, 268));

        jLabel14.setText("City:");

        jLabel16.setText("Branch Code:");

        jLabel17.setText("Street:");

        jLabel18.setText("Number:");

        javax.swing.GroupLayout editBranchPanelLayout = new javax.swing.GroupLayout(editBranchPanel);
        editBranchPanel.setLayout(editBranchPanelLayout);
        editBranchPanelLayout.setHorizontalGroup(
            editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editBranchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editBranchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editBranchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_br_city, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editBranchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_br_street, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editBranchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_br_num, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(288, Short.MAX_VALUE))
        );
        editBranchPanelLayout.setVerticalGroup(
            editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editBranchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(field_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(field_br_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(field_br_street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editBranchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(field_br_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jLabel32.setText("Destination Name:");

        jLabel33.setText("Destination ID:");

        jLabel34.setText("Location:");

        jLabel35.setText("Language:");

        jLabel36.setText("Destination Type:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        field_dst_descr.setColumns(20);
        field_dst_descr.setRows(5);
        jScrollPane3.setViewportView(field_dst_descr);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        field_dst_language.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Greek", "English", "French", "Italian", "German" }));

        field_dst_rtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOCAL", "ABROAD" }));

        field_dst_location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editDestinationPanelLayout = new javax.swing.GroupLayout(editDestinationPanel);
        editDestinationPanel.setLayout(editDestinationPanelLayout);
        editDestinationPanelLayout.setHorizontalGroup(
            editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDestinationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_dst_name, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_dst_id, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_dst_language, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_dst_rtype, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_dst_location, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        editDestinationPanelLayout.setVerticalGroup(
            editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDestinationPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editDestinationPanelLayout.createSequentialGroup()
                        .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(editDestinationPanelLayout.createSequentialGroup()
                                .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(field_dst_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32))
                            .addComponent(field_dst_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(field_dst_rtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(field_dst_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editDestinationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(field_dst_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel37.setText("AT:");

        jLabel38.setText("License:");

        jLabel39.setText("Route:");

        jLabel40.setText("Experience:");

        field_drv_route.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOCAL", "ABROAD" }));

        field_drv_license.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));

        field_drv_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editDriverPanelLayout = new javax.swing.GroupLayout(editDriverPanel);
        editDriverPanel.setLayout(editDriverPanelLayout);
        editDriverPanelLayout.setHorizontalGroup(
            editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDriverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_drv_AT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_drv_license, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_drv_route, 0, 92, Short.MAX_VALUE)
                    .addComponent(field_drv_experience))
                .addGap(318, 318, 318))
        );
        editDriverPanelLayout.setVerticalGroup(
            editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDriverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(field_drv_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(field_drv_license, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(field_drv_route, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editDriverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(field_drv_experience, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        editEventPanel.setMaximumSize(new java.awt.Dimension(526, 296));
        editEventPanel.setMinimumSize(new java.awt.Dimension(526, 296));
        editEventPanel.setPreferredSize(new java.awt.Dimension(526, 296));

        jLabel121.setText("SECONDS");

        jLabel122.setText("MINUTES");

        field_ev_end_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        field_ev_end_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel123.setText("YEAR");

        field_ev_start_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel124.setText("DAY");

        field_ev_end_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(" ------------------------ Start ------------------------");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("/");

        field_ev_end_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        field_ev_start_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel126.setText("MONTH");

        field_ev_start_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel127.setText("HOURS");

        jLabel128.setText("HOURS");

        jLabel129.setText("MINUTES");

        field_ev_end_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("/");

        field_ev_start_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel131.setText("MONTH");

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel132.setText("/");

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("/");

        jLabel134.setText("YEAR");

        jLabel135.setText("DAY");

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText(" ------------------------ End  ------------------------");
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel136.setText("SECONDS");

        field_ev_start_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_ev_start_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_ev_end_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText(":");

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel138.setText(":");

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText(":");

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText(":");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(field_ev_start_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_ev_start_year, 0, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_ev_start_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_ev_start_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(field_ev_start_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(field_ev_start_day, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(field_ev_end_hours, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_ev_end_year, 0, 61, Short.MAX_VALUE)
                                    .addComponent(jLabel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(field_ev_end_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_ev_end_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(field_ev_end_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(field_ev_end_day, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))))))
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(jLabel124)
                    .addComponent(jLabel126))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_ev_start_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel130)
                    .addComponent(field_ev_start_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125)
                    .addComponent(field_ev_start_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(jLabel129)
                    .addComponent(jLabel121))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_ev_start_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_ev_start_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_ev_start_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel137)
                    .addComponent(jLabel138))
                .addGap(8, 8, 8)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(jLabel135)
                    .addComponent(jLabel131))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_ev_end_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133)
                    .addComponent(field_ev_end_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel132)
                    .addComponent(field_ev_end_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(jLabel122)
                    .addComponent(jLabel136))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_ev_end_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_ev_end_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_ev_end_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel139)
                    .addComponent(jLabel140))
                .addGap(14, 14, 14))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        field_ev_descr.setColumns(20);
        field_ev_descr.setRows(5);
        jScrollPane7.setViewportView(field_ev_descr);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );

        jLabel8.setText("Event Trip ID:");

        field_ev_tr_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editEventPanelLayout = new javax.swing.GroupLayout(editEventPanel);
        editEventPanel.setLayout(editEventPanelLayout);
        editEventPanelLayout.setHorizontalGroup(
            editEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editEventPanelLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_ev_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 78, Short.MAX_VALUE))
        );
        editEventPanelLayout.setVerticalGroup(
            editEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editEventPanelLayout.createSequentialGroup()
                .addGroup(editEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editEventPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_ev_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jLabel41.setText("AT:");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("CV"));

        field_gui_cv.setColumns(20);
        field_gui_cv.setRows(5);
        jScrollPane6.setViewportView(field_gui_cv);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        field_gui_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editGuidePanelLayout = new javax.swing.GroupLayout(editGuidePanel);
        editGuidePanel.setLayout(editGuidePanelLayout);
        editGuidePanelLayout.setHorizontalGroup(
            editGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editGuidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editGuidePanelLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_gui_AT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        editGuidePanelLayout.setVerticalGroup(
            editGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editGuidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(field_gui_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel181.setText("SECONDS");

        jLabel182.setText("MINUTES");

        field_it_end_date_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        field_it_end_date_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel183.setText("YEAR");

        field_it_start_date_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel184.setText("DAY");

        field_it_end_date_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("----------------- Start -----------------");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel185.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel185.setText("/");

        field_it_end_date_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        field_it_start_date_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel186.setText("MONTH");

        field_it_start_date_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel187.setText("HOURS");

        jLabel188.setText("HOURS");

        jLabel189.setText("MINUTES");

        field_it_end_date_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel190.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel190.setText("/");

        field_it_start_date_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel191.setText("MONTH");

        jLabel192.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel192.setText("/");

        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel193.setText("/");

        jLabel194.setText("YEAR");

        jLabel195.setText("DAY");

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("----------------- End -----------------");
        jLabel60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel196.setText("SECONDS");

        field_it_start_date_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_it_start_date_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_it_end_date_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel197.setText(":");

        jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel198.setText(":");

        jLabel199.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel199.setText(":");

        jLabel200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel200.setText(":");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel194, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(field_it_start_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel197, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel187, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_it_start_date_year, 0, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel190, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_it_start_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel186, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel184, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel189, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_it_start_date_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel198, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(field_it_start_date_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel181, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_it_start_date_seconds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel183, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(field_it_end_date_hours, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_it_end_date_year, 0, 61, Short.MAX_VALUE)
                                    .addComponent(jLabel188, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel193, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel200, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel182, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(field_it_end_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel196, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel199, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_it_end_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(field_it_end_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel192, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel191, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel195, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(field_it_end_date_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(42, 42, 42))))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel194)
                    .addComponent(jLabel184)
                    .addComponent(jLabel186))
                .addGap(7, 7, 7)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_it_start_date_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel190)
                    .addComponent(field_it_start_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel185)
                    .addComponent(field_it_start_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel187)
                    .addComponent(jLabel189)
                    .addComponent(jLabel181))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_it_start_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_it_start_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_it_start_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel197)
                    .addComponent(jLabel198))
                .addGap(8, 8, 8)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel183)
                    .addComponent(jLabel195)
                    .addComponent(jLabel191))
                .addGap(7, 7, 7)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_it_end_date_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel193)
                    .addComponent(field_it_end_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel192)
                    .addComponent(field_it_end_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel188)
                    .addComponent(jLabel182)
                    .addComponent(jLabel196))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_it_end_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_it_end_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_it_end_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel199)
                    .addComponent(jLabel200))
                .addGap(14, 14, 14))
        );

        jLabel24.setText("IT Worker ID:");

        jLabel25.setText("Username:");

        jLabel26.setText("Password:");

        field_it_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editItWorkerPanelLayout = new javax.swing.GroupLayout(editItWorkerPanel);
        editItWorkerPanel.setLayout(editItWorkerPanelLayout);
        editItWorkerPanelLayout.setHorizontalGroup(
            editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editItWorkerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_it_username, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(field_it_password, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(field_it_AT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        editItWorkerPanelLayout.setVerticalGroup(
            editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editItWorkerPanelLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(editItWorkerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(field_it_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(field_it_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(field_it_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel42.setText("Language:");

        field_lng_gui_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_lng_language.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Greek", "English", "French", "Italian", "German" }));

        jLabel75.setText("Guide AT:");

        javax.swing.GroupLayout editLanguagesPanelLayout = new javax.swing.GroupLayout(editLanguagesPanel);
        editLanguagesPanel.setLayout(editLanguagesPanelLayout);
        editLanguagesPanelLayout.setHorizontalGroup(
            editLanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLanguagesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editLanguagesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_lng_language, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editLanguagesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_lng_gui_AT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        editLanguagesPanelLayout.setVerticalGroup(
            editLanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLanguagesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_lng_gui_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(16, 16, 16)
                .addGroup(editLanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addComponent(field_lng_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jLabel201.setText("SECONDS");

        field_log_time_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel204.setText("DAY");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("----------------- Time -----------------");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel205.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel205.setText("/");

        field_log_time_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel206.setText("MONTH");

        field_log_time_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel207.setText("HOURS");

        jLabel209.setText("MINUTES");

        jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel210.setText("/");

        field_log_time_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel214.setText("YEAR");

        field_log_time_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_log_time_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel217.setText(":");

        jLabel218.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel218.setText(":");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel214, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(field_log_time_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel217, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel207, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_log_time_year, 0, 61, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_log_time_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel206, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel209, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(field_log_time_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel218, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_log_time_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel201, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(field_log_time_seconds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel214)
                    .addComponent(jLabel204)
                    .addComponent(jLabel206))
                .addGap(7, 7, 7)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_log_time_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel210)
                    .addComponent(field_log_time_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel205)
                    .addComponent(field_log_time_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel207)
                    .addComponent(jLabel209)
                    .addComponent(jLabel201))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_log_time_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_log_time_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_log_time_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel217)
                    .addComponent(jLabel218))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setText("Log Entry ID:");

        jLabel47.setText("IT Worker ID:");

        jLabel48.setText("Table:");

        jLabel49.setText("Action:");

        field_log_it_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_log_table.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRIP", "RESERVATION", "EVENT", "TRAVEL_TO", "DESTINATION" }));

        field_log_action.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INSERTED", "UPDATED", "DELETED" }));

        javax.swing.GroupLayout editLogPanelLayout = new javax.swing.GroupLayout(editLogPanel);
        editLogPanel.setLayout(editLogPanelLayout);
        editLogPanelLayout.setHorizontalGroup(
            editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLogPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editLogPanelLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_log_entry_id))
                    .addGroup(editLogPanelLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_log_it_AT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editLogPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_log_table, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editLogPanelLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_log_action, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        editLogPanelLayout.setVerticalGroup(
            editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLogPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editLogPanelLayout.createSequentialGroup()
                        .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(field_log_entry_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(field_log_it_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(field_log_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(field_log_action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jLabel45.setText("Admin AT:");

        jLabel46.setText("Branch Code:");

        field_mng_adm_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_mng_br_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editManagesPanelLayout = new javax.swing.GroupLayout(editManagesPanel);
        editManagesPanel.setLayout(editManagesPanelLayout);
        editManagesPanelLayout.setHorizontalGroup(
            editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editManagesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_mng_adm_AT, 0, 90, Short.MAX_VALUE)
                    .addComponent(field_mng_br_code, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        editManagesPanelLayout.setVerticalGroup(
            editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editManagesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(field_mng_adm_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editManagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(field_mng_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jLabel161.setText("SECONDS");

        jLabel162.setText("MINUTES");

        field_off_end_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        field_off_end_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel163.setText("YEAR");

        field_off_start_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel164.setText("DAY");

        field_off_end_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("----------------- Start -----------------");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("/");

        field_off_end_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        field_off_start_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel166.setText("MONTH");

        field_off_start_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel167.setText("HOURS");

        jLabel168.setText("HOURS");

        jLabel169.setText("MINUTES");

        field_off_end_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel170.setText("/");

        field_off_start_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel171.setText("MONTH");

        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel172.setText("/");

        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel173.setText("/");

        jLabel174.setText("YEAR");

        jLabel175.setText("DAY");

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("----------------- End -----------------");
        jLabel59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel176.setText("SECONDS");

        field_off_start_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_off_start_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_off_end_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel177.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel177.setText(":");

        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel178.setText(":");

        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel179.setText(":");

        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel180.setText(":");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(field_off_start_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel167, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_off_start_year, 0, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel170, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_off_start_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel169, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_off_start_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel165, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel178, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(field_off_start_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel161, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_off_start_seconds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(field_off_end_hours, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_off_end_year, 0, 61, Short.MAX_VALUE)
                                    .addComponent(jLabel168, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel173, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel180, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(field_off_end_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel179, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_off_end_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(field_off_end_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel172, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel171, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(field_off_end_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(42, 42, 42))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel174)
                    .addComponent(jLabel164)
                    .addComponent(jLabel166))
                .addGap(7, 7, 7)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_off_start_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel170)
                    .addComponent(field_off_start_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel165)
                    .addComponent(field_off_start_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel167)
                    .addComponent(jLabel169)
                    .addComponent(jLabel161))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_off_start_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_off_start_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_off_start_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel177)
                    .addComponent(jLabel178))
                .addGap(8, 8, 8)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel163)
                    .addComponent(jLabel175)
                    .addComponent(jLabel171))
                .addGap(7, 7, 7)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_off_end_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel173)
                    .addComponent(field_off_end_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel172)
                    .addComponent(field_off_end_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel168)
                    .addComponent(jLabel162)
                    .addComponent(jLabel176))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_off_end_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_off_end_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_off_end_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel179)
                    .addComponent(jLabel180))
                .addGap(14, 14, 14))
        );

        jLabel50.setText("Offer ID:");

        jLabel51.setText("Offer Destination:");

        jLabel52.setText("Offer Cost:");

        field_off_destination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editOfferPanelLayout = new javax.swing.GroupLayout(editOfferPanel);
        editOfferPanel.setLayout(editOfferPanelLayout);
        editOfferPanelLayout.setHorizontalGroup(
            editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editOfferPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_off_cost)
                    .addComponent(field_off_destination, 0, 69, Short.MAX_VALUE)
                    .addComponent(field_off_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        editOfferPanelLayout.setVerticalGroup(
            editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editOfferPanelLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(editOfferPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(field_off_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(field_off_destination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(field_off_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setText("Branch ID:");

        jLabel44.setText("Phone Number:");

        field_ph_br_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editPhonesPanelLayout = new javax.swing.GroupLayout(editPhonesPanel);
        editPhonesPanel.setLayout(editPhonesPanelLayout);
        editPhonesPanelLayout.setHorizontalGroup(
            editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPhonesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_ph_number)
                    .addComponent(field_ph_br_code, 0, 90, Short.MAX_VALUE))
                .addContainerGap(298, Short.MAX_VALUE))
        );
        editPhonesPanelLayout.setVerticalGroup(
            editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPhonesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(field_ph_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editPhonesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(field_ph_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        editTripPanel.setMaximumSize(new java.awt.Dimension(500, 268));
        editTripPanel.setMinimumSize(new java.awt.Dimension(500, 268));

        jLabel9.setText("Trip ID:");

        jLabel10.setText("Max Seats:");

        jLabel11.setText("Cost");

        jLabel12.setText("Branch:");

        jLabel13.setText("Driver:");

        jLabel28.setText("Guide:");

        field_tr_br_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_tr_gui_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_tr_drv_AT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel101.setText("SECONDS");

        jLabel102.setText("MINUTES");

        field_tr_return_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        field_tr_return_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel103.setText("YEAR");

        field_tr_departure_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel104.setText("DAY");

        field_tr_return_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("----------------- Departure -----------------");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("/");

        field_tr_return_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        field_tr_departure_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel106.setText("MONTH");

        field_tr_departure_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel107.setText("HOURS");

        jLabel108.setText("HOURS");

        jLabel109.setText("MINUTES");

        field_tr_return_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("/");

        field_tr_departure_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel111.setText("MONTH");

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("/");

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("/");

        jLabel114.setText("YEAR");

        jLabel115.setText("DAY");

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("----------------- Return -----------------");
        jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel116.setText("SECONDS");

        field_tr_departure_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_tr_departure_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_tr_return_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText(":");

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText(":");

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText(":");

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText(":");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(field_tr_departure_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_tr_departure_year, 0, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_tr_departure_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_tr_departure_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(field_tr_departure_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_tr_departure_seconds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(field_tr_return_hours, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_tr_return_year, 0, 61, Short.MAX_VALUE)
                                    .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(field_tr_return_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_tr_return_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(field_tr_return_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(field_tr_return_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(42, 42, 42))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(jLabel104)
                    .addComponent(jLabel106))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_tr_departure_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110)
                    .addComponent(field_tr_departure_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105)
                    .addComponent(field_tr_departure_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(jLabel109)
                    .addComponent(jLabel101))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_tr_departure_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_tr_departure_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_tr_departure_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117)
                    .addComponent(jLabel118))
                .addGap(8, 8, 8)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel115)
                    .addComponent(jLabel111))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_tr_return_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113)
                    .addComponent(field_tr_return_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112)
                    .addComponent(field_tr_return_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jLabel102)
                    .addComponent(jLabel116))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_tr_return_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_tr_return_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_tr_return_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel119)
                    .addComponent(jLabel120))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout editTripPanelLayout = new javax.swing.GroupLayout(editTripPanel);
        editTripPanel.setLayout(editTripPanelLayout);
        editTripPanelLayout.setHorizontalGroup(
            editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTripPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_tr_maxseats, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_tr_cost, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_tr_id, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTripPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTripPanelLayout.createSequentialGroup()
                        .addGroup(editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTripPanelLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addComponent(field_tr_gui_AT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_tr_br_code, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_tr_drv_AT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        editTripPanelLayout.setVerticalGroup(
            editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTripPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTripPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_maxseats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editTripPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_gui_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editTripPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTripPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_drv_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTripPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_tr_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
            .addGroup(editTripPanelLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jLabel65.setText("Trip ID:");

        jLabel66.setText("Seat:");

        jLabel67.setText("Name:");

        jLabel68.setText("Lastname:");

        jLabel69.setText("Is Adult:");

        field_res_tr_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_res_isadult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADULT", "MINOR" }));

        javax.swing.GroupLayout editReservationPanelLayout = new javax.swing.GroupLayout(editReservationPanel);
        editReservationPanel.setLayout(editReservationPanelLayout);
        editReservationPanelLayout.setHorizontalGroup(
            editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editReservationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editReservationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_res_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_res_isadult, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editReservationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_res_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_res_seatnum, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_res_name, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(288, Short.MAX_VALUE))
        );
        editReservationPanelLayout.setVerticalGroup(
            editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editReservationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(field_res_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(field_res_seatnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(field_res_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(field_res_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(field_res_isadult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        field_resoff_off_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel70.setText("ID:");

        jLabel71.setText("Offer ID:");

        jLabel72.setText("Name:");

        jLabel73.setText("Lastname:");

        jLabel74.setText("Deposit:");

        javax.swing.GroupLayout editReservationOfferPanelLayout = new javax.swing.GroupLayout(editReservationOfferPanel);
        editReservationOfferPanel.setLayout(editReservationOfferPanelLayout);
        editReservationOfferPanelLayout.setHorizontalGroup(
            editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field_resoff_id, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_resoff_off_id, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_resoff_name, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_resoff_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_resoff_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(304, Short.MAX_VALUE))
        );
        editReservationOfferPanelLayout.setVerticalGroup(
            editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editReservationOfferPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(field_resoff_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(field_resoff_off_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(field_resoff_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(field_resoff_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editReservationOfferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(field_resoff_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jLabel141.setText("SECONDS");

        jLabel142.setText("MINUTES");

        field_to_departure_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        field_to_departure_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel143.setText("YEAR");

        field_to_arrival_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel144.setText("DAY");

        field_to_departure_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("----------------- Arrival -----------------");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel145.setText("/");

        field_to_departure_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        field_to_arrival_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel146.setText("MONTH");

        field_to_arrival_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel147.setText("HOURS");

        jLabel148.setText("HOURS");

        jLabel149.setText("MINUTES");

        field_to_departure_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setText("/");

        field_to_arrival_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel151.setText("MONTH");

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel152.setText("/");

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("/");

        jLabel154.setText("YEAR");

        jLabel155.setText("DAY");

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("----------------- Departure -----------------");
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel156.setText("SECONDS");

        field_to_arrival_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_to_arrival_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        field_to_departure_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel157.setText(":");

        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setText(":");

        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel159.setText(":");

        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText(":");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(field_to_arrival_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel147, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_to_arrival_year, 0, 61, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_to_arrival_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_to_arrival_month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(field_to_arrival_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(field_to_arrival_seconds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(field_to_departure_hours, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(field_to_departure_year, 0, 61, Short.MAX_VALUE)
                                    .addComponent(jLabel148, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(field_to_departure_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_to_departure_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(field_to_departure_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(field_to_departure_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(42, 42, 42))))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel154)
                    .addComponent(jLabel144)
                    .addComponent(jLabel146))
                .addGap(7, 7, 7)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_to_arrival_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel150)
                    .addComponent(field_to_arrival_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel145)
                    .addComponent(field_to_arrival_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel147)
                    .addComponent(jLabel149)
                    .addComponent(jLabel141))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_to_arrival_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_to_arrival_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_to_arrival_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel157)
                    .addComponent(jLabel158))
                .addGap(8, 8, 8)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel143)
                    .addComponent(jLabel155)
                    .addComponent(jLabel151))
                .addGap(7, 7, 7)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_to_departure_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153)
                    .addComponent(field_to_departure_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel152)
                    .addComponent(field_to_departure_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel148)
                    .addComponent(jLabel142)
                    .addComponent(jLabel156))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_to_departure_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_to_departure_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_to_departure_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159)
                    .addComponent(jLabel160))
                .addGap(14, 14, 14))
        );

        jLabel63.setText("Trip ID:");

        jLabel64.setText("Destination ID:");

        field_to_tr_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        field_to_dst_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editTravelToPanelLayout = new javax.swing.GroupLayout(editTravelToPanel);
        editTravelToPanel.setLayout(editTravelToPanelLayout);
        editTravelToPanelLayout.setHorizontalGroup(
            editTravelToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTravelToPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTravelToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTravelToPanelLayout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_to_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editTravelToPanelLayout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(field_to_dst_id, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        editTravelToPanelLayout.setVerticalGroup(
            editTravelToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTravelToPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTravelToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(field_to_tr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editTravelToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(field_to_dst_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTravelToPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel53.setText("AT:");

        jLabel54.setText("Name:");

        jLabel55.setText("Lastname:");

        jLabel61.setText("Salary:");

        jLabel62.setText("Branch:");

        field_wrk_br_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout editWorkerPanelLayout = new javax.swing.GroupLayout(editWorkerPanel);
        editWorkerPanel.setLayout(editWorkerPanelLayout);
        editWorkerPanelLayout.setHorizontalGroup(
            editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editWorkerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_wrk_salary)
                    .addComponent(field_wrk_br_code, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_wrk_AT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_wrk_name)
                    .addComponent(field_wrk_lname))
                .addGap(294, 294, 294))
        );
        editWorkerPanelLayout.setVerticalGroup(
            editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editWorkerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(field_wrk_AT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(field_wrk_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(field_wrk_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(field_wrk_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editWorkerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(field_wrk_br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jLayeredPane2.setLayer(editAdminPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editBranchPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editDestinationPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editDriverPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editEventPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editGuidePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editItWorkerPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editLanguagesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editLogPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editManagesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editOfferPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editPhonesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editTripPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editReservationPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editReservationOfferPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editTravelToPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(editWorkerPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(editItWorkerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editBranchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editReservationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editPhonesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editAdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editDestinationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editDriverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editEventPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editGuidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editLanguagesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editManagesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editOfferPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(editTripPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editReservationOfferPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editTravelToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editWorkerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(editItWorkerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editBranchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editReservationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 36, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editPhonesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editAdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editDestinationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editDriverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editEventPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editGuidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editLanguagesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editManagesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editOfferPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editTripPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editReservationOfferPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editTravelToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(editWorkerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        cancelChangesButton.setText("Cancel");
        cancelChangesButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelChangesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editDialogLayout = new javax.swing.GroupLayout(editDialog.getContentPane());
        editDialog.getContentPane().setLayout(editDialogLayout);
        editDialogLayout.setHorizontalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editJabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveChangesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelChangesButton)
                .addGap(25, 25, 25))
        );
        editDialogLayout.setVerticalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDialogLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(editJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveChangesButton)
                    .addComponent(cancelChangesButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout errorDialogLayout = new javax.swing.GroupLayout(errorDialog.getContentPane());
        errorDialog.getContentPane().setLayout(errorDialogLayout);
        errorDialogLayout.setHorizontalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(errorOptionPane, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );
        errorDialogLayout.setVerticalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(errorOptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addItWorkerDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addItWorkerDialog.setTitle("Add IT Worker");
        addItWorkerDialog.setAlwaysOnTop(true);
        addItWorkerDialog.setResizable(false);
        addItWorkerDialog.setSize(new java.awt.Dimension(500, 400));

        add_it_name.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        add_it_name.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                add_it_nameActionPerformed(evt);
            }
        });

        add_it_at.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        add_it_salary.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        add_it_lname.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        add_it_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setText("AT:");

        jLabel87.setText("Name:");

        jLabel88.setText("Lastname:");

        jLabel89.setText("Salary:");

        jLabel90.setText("Branch:");

        jLabel91.setText("Username:");

        jLabel92.setText("Password:");

        editJabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        editJabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editJabel1.setText("Add IT Worker");
        editJabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel203.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel203.setText(":");

        jLabel222.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel222.setText(":");

        add_it_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel223.setText("MONTH");

        add_it_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel224.setText("HOURS");

        jLabel225.setText("MINUTES");

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel226.setText("/");

        add_it_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel227.setText("SECONDS");

        jLabel228.setText("YEAR");

        add_it_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        add_it_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel229.setText("DAY");

        add_it_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("----------------- Start -----------------");
        jLabel93.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel230.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel230.setText("/");

        saveChangesButton1.setText("Save Changes");
        saveChangesButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveChangesButton1ActionPerformed(evt);
            }
        });

        cancelChangesButton1.setText("Cancel");
        cancelChangesButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelChangesButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addItWorkerDialogLayout = new javax.swing.GroupLayout(addItWorkerDialog.getContentPane());
        addItWorkerDialog.getContentPane().setLayout(addItWorkerDialogLayout);
        addItWorkerDialogLayout.setHorizontalGroup(
            addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editJabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_it_salary)
                            .addComponent(add_it_branch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add_it_at, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_it_name)
                            .addComponent(add_it_lname))
                        .addGap(27, 27, 27)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel228, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                        .addComponent(add_it_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel224, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(add_it_year, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel226, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(add_it_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel223, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel229, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel225, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(add_it_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel230, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel222, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(add_it_day, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel227, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(add_it_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(add_it_password, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(add_it_username)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addItWorkerDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveChangesButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelChangesButton1)))
                .addContainerGap())
        );
        addItWorkerDialogLayout.setVerticalGroup(
            addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editJabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(add_it_at, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(add_it_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel88)
                            .addComponent(add_it_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add_it_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89))
                        .addGap(22, 22, 22)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(add_it_branch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addItWorkerDialogLayout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel228)
                            .addComponent(jLabel229)
                            .addComponent(jLabel223))
                        .addGap(7, 7, 7)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add_it_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel226)
                            .addComponent(add_it_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel230)
                            .addComponent(add_it_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel224)
                            .addComponent(jLabel225)
                            .addComponent(jLabel227))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add_it_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_it_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_it_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel203)
                            .addComponent(jLabel222))
                        .addGap(18, 18, 18)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(add_it_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(add_it_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(addItWorkerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveChangesButton1)
                    .addComponent(cancelChangesButton1))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        welcomeLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        welcomeLabel.setText("Welcome");

        profileButton.setBackground(new java.awt.Color(204, 255, 255));
        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/profile.png"))); // NOI18N
        profileButton.setToolTipText("Profile");
        profileButton.setBorder(null);
        profileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                profileButtonActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(204, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png"))); // NOI18N
        logoutButton.setToolTipText("Logout");
        logoutButton.setBorder(null);
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setBorder(null);

        mainList.setBackground(new java.awt.Color(204, 255, 255));
        mainList.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Show Tables", "Trips Per Branch", "Branch Info", "Profit Per Branch", "Reservation Offers", "Add IT Worker", "Show Log" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        mainList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mainList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainList.setDragEnabled(true);
        mainList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                mainListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(mainList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        mainTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        mainTable.setDragEnabled(true);
        mainTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mainTable.setShowGrid(false);
        jScrollPane2.setViewportView(mainTable);

        jLabel6.setText("Select table: ");

        tableComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "admin", "branch", "destination", "driver", "event", "guide", "it_worker", "languages", "log", "manages", "offer", "phones", "reservation", "reservation_offer", "travel_to", "trip", "worker" }));
        tableComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                tableComboBoxItemStateChanged(evt);
            }
        });

        editButton.setText("Edit selected row");
        editButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete selected row");
        deleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteButtonActionPerformed(evt);
            }
        });

        insertButton.setText("Insert new row");
        insertButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                insertButtonActionPerformed(evt);
            }
        });

        rowCountJabel.setText("Number of table entries: ");

        javax.swing.GroupLayout panelOption0Layout = new javax.swing.GroupLayout(panelOption0);
        panelOption0.setLayout(panelOption0Layout);
        panelOption0Layout.setHorizontalGroup(
            panelOption0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption0Layout.createSequentialGroup()
                .addGroup(panelOption0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(panelOption0Layout.createSequentialGroup()
                        .addGroup(panelOption0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rowCountJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelOption0Layout.createSequentialGroup()
                                .addComponent(insertButton)
                                .addGap(18, 18, 18)
                                .addComponent(editButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOption0Layout.setVerticalGroup(
            panelOption0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption0Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rowCountJabel)
                .addGap(18, 18, 18)
                .addGroup(panelOption0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        branchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        branchTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        branchTable.setDragEnabled(true);
        branchTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        branchTable.setShowGrid(false);
        jScrollPane5.setViewportView(branchTable);

        branchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        branchButton.setText("Run");
        branchButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                branchButtonActionPerformed(evt);
            }
        });

        jLabel76.setText("Select branch:");

        jLabel202.setText("SECONDS");

        branch_start_date_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel208.setText("DAY");

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("--------------- Start Date ---------------");
        jLabel77.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        branch_start_date_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel212.setText("MONTH");

        branch_start_date_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel213.setText("HOURS");

        jLabel215.setText("MINUTES");

        branch_start_date_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel219.setText("YEAR");

        branch_start_date_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        branch_start_date_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel219, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_start_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel213, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(branch_start_date_year, 0, 61, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel212, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel208, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel215, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel202, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(branch_start_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branch_start_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(branch_start_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branch_start_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel219)
                    .addComponent(jLabel208)
                    .addComponent(jLabel212))
                .addGap(7, 7, 7)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branch_start_date_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_start_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_start_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel213)
                    .addComponent(jLabel215)
                    .addComponent(jLabel202))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branch_start_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_start_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_start_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel211.setText("SECONDS");

        branch_end_date_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel216.setText("DAY");

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("--------------- End Date ---------------");
        jLabel84.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        branch_end_date_hours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel220.setText("MONTH");

        branch_end_date_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel221.setText("HOURS");

        jLabel281.setText("MINUTES");

        branch_end_date_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel282.setText("YEAR");

        branch_end_date_minutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        branch_end_date_seconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel282, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_end_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel221, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(branch_end_date_year, 0, 61, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel220, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel216, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel281, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel211, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(branch_end_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branch_end_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(branch_end_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branch_end_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel282)
                    .addComponent(jLabel216)
                    .addComponent(jLabel220))
                .addGap(7, 7, 7)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branch_end_date_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_end_date_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_end_date_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel221)
                    .addComponent(jLabel281)
                    .addComponent(jLabel211))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branch_end_date_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_end_date_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branch_end_date_seconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        branchButtonSelect.setText("Select All");
        branchButtonSelect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                branchButtonSelectActionPerformed(evt);
            }
        });

        branchButtonEmpty.setText("Empty Date Fields");
        branchButtonEmpty.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                branchButtonEmptyActionPerformed(evt);
            }
        });

        branchRowCountJabel.setText("Number of table entries:");

        javax.swing.GroupLayout panelOption1Layout = new javax.swing.GroupLayout(panelOption1);
        panelOption1.setLayout(panelOption1Layout);
        panelOption1Layout.setHorizontalGroup(
            panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption1Layout.createSequentialGroup()
                .addGroup(panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelOption1Layout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addGap(18, 18, 18)
                                .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelOption1Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption1Layout.createSequentialGroup()
                                .addComponent(branchButtonSelect)
                                .addGap(18, 18, 18)
                                .addComponent(branchButtonEmpty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(branchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(panelOption1Layout.createSequentialGroup()
                        .addComponent(branchRowCountJabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOption1Layout.setVerticalGroup(
            panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOption1Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(branchRowCountJabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelOption1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(branchButton)
                            .addComponent(branchButtonSelect)
                            .addComponent(branchButtonEmpty))
                        .addGap(26, 26, 26))
                    .addGroup(panelOption1Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        brinfoEmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        brinfoEmployeeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        brinfoEmployeeTable.setDragEnabled(true);
        brinfoEmployeeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        brinfoEmployeeTable.setShowGrid(false);
        jScrollPane9.setViewportView(brinfoEmployeeTable);

        jLabel86.setText("Select branch: ");

        brinfoComboBox.setMaximumSize(new java.awt.Dimension(117, 22));
        brinfoComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                brinfoComboBoxItemStateChanged(evt);
            }
        });

        brinfoEmployeeJabel.setText("Number of employees: ");

        brinfoSalarJabel.setText("Total salary:");

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Employees:");

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("Manager:");

        brinfoManagerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        brinfoManagerTable.setShowGrid(false);
        jScrollPane10.setViewportView(brinfoManagerTable);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Branch Info:");

        brinfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        brinfoTable.setShowGrid(false);
        jScrollPane11.setViewportView(brinfoTable);

        jLabel82.setText(" ");

        javax.swing.GroupLayout panelOption2Layout = new javax.swing.GroupLayout(panelOption2);
        panelOption2.setLayout(panelOption2Layout);
        panelOption2Layout.setHorizontalGroup(
            panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption2Layout.createSequentialGroup()
                .addGroup(panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption2Layout.createSequentialGroup()
                        .addComponent(brinfoEmployeeJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(brinfoSalarJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9)
                    .addGroup(panelOption2Layout.createSequentialGroup()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brinfoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption2Layout.createSequentialGroup()
                    .addContainerGap(420, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        panelOption2Layout.setVerticalGroup(
            panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption2Layout.createSequentialGroup()
                .addGroup(panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(brinfoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brinfoEmployeeJabel)
                    .addComponent(brinfoSalarJabel))
                .addGap(47, 47, 47))
            .addGroup(panelOption2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption2Layout.createSequentialGroup()
                    .addContainerGap(435, Short.MAX_VALUE)
                    .addComponent(jLabel82)
                    .addGap(20, 20, 20)))
        );

        expensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null}
            },
            new String []
            {
                "Title 1", "Title 2"
            }
        ));
        expensesTable.setShowGrid(false);
        jScrollPane8.setViewportView(expensesTable);

        profitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        profitTable.setShowGrid(false);
        jScrollPane14.setViewportView(profitTable);

        jLabel94.setText("Income:");

        jLabel95.setText("Expenses:");

        profitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        profitComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                profitComboBoxItemStateChanged(evt);
            }
        });

        jLabel96.setText("Select branch:");

        incomeJabel.setText("Total Income:");

        expensesJabel.setText("Total Expenses:");

        profitJabel.setText("Profit:");

        reservationJabel.setText("Number of Reservations:");

        expenseEmployeeJabel.setText("Number of Employees:");

        javax.swing.GroupLayout panelOption3Layout = new javax.swing.GroupLayout(panelOption3);
        panelOption3.setLayout(panelOption3Layout);
        panelOption3Layout.setHorizontalGroup(
            panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOption3Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addGap(18, 18, 18)
                        .addComponent(profitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(incomeJabel, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(expensesJabel, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(profitJabel, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption3Layout.createSequentialGroup()
                        .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(reservationJabel, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expenseEmployeeJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
        );
        panelOption3Layout.setVerticalGroup(
            panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(profitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(panelOption3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservationJabel)
                    .addComponent(expenseEmployeeJabel))
                .addGap(18, 18, 18)
                .addComponent(incomeJabel)
                .addGap(18, 18, 18)
                .addComponent(expensesJabel)
                .addGap(18, 18, 18)
                .addComponent(profitJabel)
                .addGap(38, 38, 38))
        );

        resoffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        resoffTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        resoffTable.setDragEnabled(true);
        resoffTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resoffTable.setShowGrid(false);
        jScrollPane15.setViewportView(resoffTable);

        jLabel97.setText("Enter lastname:");

        resoff_run_button.setText("Run");
        resoff_run_button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                resoff_run_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOption4Layout = new javax.swing.GroupLayout(panelOption4);
        panelOption4.setLayout(panelOption4Layout);
        panelOption4Layout.setHorizontalGroup(
            panelOption4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption4Layout.createSequentialGroup()
                .addGroup(panelOption4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(panelOption4Layout.createSequentialGroup()
                        .addGroup(panelOption4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(resoff_lname_field)
                            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(resoff_run_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOption4Layout.setVerticalGroup(
            panelOption4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOption4Layout.createSequentialGroup()
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOption4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resoff_lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resoff_run_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        addWorkerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        addWorkerTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        addWorkerTable.setDragEnabled(true);
        addWorkerTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        addWorkerTable.setShowGrid(false);
        jScrollPane12.setViewportView(addWorkerTable);

        addItTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        addItTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        addItTable.setDragEnabled(true);
        addItTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        addItTable.setShowGrid(false);
        jScrollPane13.setViewportView(addItTable);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Workers:");

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("IT Workers:");

        addNewItWorkerButton.setText("Add New IT Worker");
        addNewItWorkerButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addNewItWorkerButtonActionPerformed(evt);
            }
        });

        transferWorkerButton.setText("Transfer Existing Worker");
        transferWorkerButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                transferWorkerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOption5Layout = new javax.swing.GroupLayout(panelOption5);
        panelOption5.setLayout(panelOption5Layout);
        panelOption5Layout.setHorizontalGroup(
            panelOption5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption5Layout.createSequentialGroup()
                .addGroup(panelOption5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelOption5Layout.createSequentialGroup()
                        .addComponent(transferWorkerButton)
                        .addGap(18, 18, 18)
                        .addComponent(addNewItWorkerButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOption5Layout.setVerticalGroup(
            panelOption5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption5Layout.createSequentialGroup()
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelOption5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferWorkerButton)
                    .addComponent(addNewItWorkerButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        logTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        logTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        logTable.setDragEnabled(true);
        logTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        logTable.setShowGrid(false);
        jScrollPane4.setViewportView(logTable);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-- LOG --");

        logCountJabel.setText("Entries in log:");

        emptyLogButton.setText("Empty Log");
        emptyLogButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                emptyLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOption6Layout = new javax.swing.GroupLayout(panelOption6);
        panelOption6.setLayout(panelOption6Layout);
        panelOption6Layout.setHorizontalGroup(
            panelOption6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption6Layout.createSequentialGroup()
                .addGroup(panelOption6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelOption6Layout.createSequentialGroup()
                        .addComponent(logCountJabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emptyLogButton)))
                .addContainerGap())
        );
        panelOption6Layout.setVerticalGroup(
            panelOption6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOption6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOption6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logCountJabel)
                    .addComponent(emptyLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(panelOption0, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelOption6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOption1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOption1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOption6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLayeredPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_profileButtonActionPerformed
    {//GEN-HEADEREND:event_profileButtonActionPerformed
		profileDialog.setVisible(true);
    }//GEN-LAST:event_profileButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_logoutButtonActionPerformed
    {//GEN-HEADEREND:event_logoutButtonActionPerformed
		int opt = logoutOptionPane.YES_NO_OPTION;
		opt = logoutOptionPane.showConfirmDialog(null, "Are you sure you want to log out?","WARNING", opt);
		
		if (opt == logoutOptionPane.YES_OPTION)
		{
			this.dispose();
			LoginWindow.loginWindow();
		}
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void mainListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_mainListValueChanged
    {//GEN-HEADEREND:event_mainListValueChanged
		if (currentPanel != null)
			currentPanel.setVisible(false);
		
		switch(mainList.getSelectedIndex())
        {
            case(0): //Show tables
            {
				currentPanel = panelOption0;
				
				try
				{
					if (tableComboBox.getSelectedIndex() > 0)
						updateCurrentTable();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
            }
            break;

            case(1): //Trips per branch
            {
				currentPanel = panelOption1;
				branchComboBox.removeAllItems();
				setupComboBoxFromQuery(branchComboBox, new DatabaseQuery("select br_code from branch order by br_code asc"));
				branchComboBox.setSelectedIndex(selectedBranch);
				
				tripsPerBranch();
            }
            break;

            case(2): //Branch info
            {
				currentPanel = panelOption2;
				
				brinfoComboBox.removeAllItems();
				brinfoComboBox.addItem("NONE");
				setupComboBoxFromQuery(brinfoComboBox, new DatabaseQuery("select br_code from branch order by br_code asc"));
				
				//after this, control gets transferred to brinfoComboBoxItemStateChanged
            }
            break;

            case(3): //Profit per branch
            {
				currentPanel = panelOption3;
				
				profitComboBox.removeAllItems();
				profitComboBox.addItem("NONE");
				setupComboBoxFromQuery(profitComboBox, new DatabaseQuery("select br_code from branch order by br_code asc"));
				
				emptyTable(profitTable, 11, 4, tableWidth);
				emptyTable(expensesTable,11, 2, tableWidth);
				
				//after this, control gets transferred to profitComboBoxItemStateChanged
            }
            break;

            case(4): //Reservation offers
            {
				currentPanel = panelOption4;
				
				String lname = resoff_lname_field.getText();
				if (!lname.equals(""))
				{
					resoff_run_buttonActionPerformed(null);
				}
				else emptyTable(resoffTable, 19, 4, tableWidth);	
            }
            break;

            case(5): //Add it worker
            {
				currentPanel = panelOption5;
				
				emptyTable(addItTable, 8, 4, tableWidth);
				emptyTable(addWorkerTable, 8, 4, tableWidth);
				
				addWorkerTable.setEnabled(true);
				
				addItWorkerPanel();
            }
            break;

            case(6): //Log
            {
				currentPanel = panelOption6;
				
				try
				{
					Main.table(LOG).setRowCount(insertIntoTableFromMain(logTable, Main.table(LOG)));
					logCountJabel.setText("Entries in log: " + String.valueOf(Main.table(LOG).rowCount()));
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
            }
            break;
        }
		
		currentPanel.setVisible(true);
    }//GEN-LAST:event_mainListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteButtonActionPerformed
    {//GEN-HEADEREND:event_deleteButtonActionPerformed
        if (mainTable.getSelectedRow() != -1)
		{
			int opt = rowOperationOptionPane.YES_NO_OPTION;
			opt = rowOperationOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected row? This action cannot be undone!","WARNING", opt);

			if (opt == rowOperationOptionPane.YES_OPTION)
			{
				try
				{
					if (currentTable == ADMIN)
					{
						LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
						String admAt = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
						
						values.add(new Pair<Object, Integer>(admAt, Types.CHAR));
						DatabaseQuery query = new DatabaseQuery("select wrk_name, wrk_lname from worker where wrk_AT = ?", values);
						query.execute();
						Main.res.next();
						
						values = new LinkedList<Pair<Object, Integer>>();
						
						values.add(new Pair<Object, Integer>(Main.res.getString(1), Types.VARCHAR));
						values.add(new Pair<Object, Integer>(Main.res.getString(2), Types.VARCHAR));
						DatabaseCall call = new DatabaseCall("delete_admin", values);
						
						call.execute();
						Main.res.next();
						throwError(Main.res.getString(1));
					}
					else
					{
						String query = "DELETE FROM " + Main.table(currentTable).name() + " " + Main.table(currentTable).where();

						int r = mainTable.getSelectedRow();

						Main.statement = Main.connection().prepareStatement(query);

						LinkedList<Integer> keys = Main.table(currentTable).keys();
						int i = 1;
						for (Integer p : keys)
						{	
							Main.statement.setObject(i, mainTable.getValueAt(r, p));
							i++;
						}

						//System.out.println(query);
						Main.statement.executeUpdate();
					}
					
					updateCurrentTable();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}
		else
		{
			rowOperationOptionPane.showConfirmDialog(null, "Please select a row to delete first.","WARNING", rowOperationOptionPane.OK_CANCEL_OPTION);
		}
		
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void tableComboBoxItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_tableComboBoxItemStateChanged
    {//GEN-HEADEREND:event_tableComboBoxItemStateChanged
		if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
		{
			try
			{
				if (tableComboBox.getSelectedIndex() == 0)
				{
					currentTable = -1;
					rowCountJabel.setText("Number of table entries: 0");
					emptyTable(mainTable, tableDefaultRowCount, tableDefaultColumnCount, tableWidth);
				}
				else updateCurrentTable();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
    }//GEN-LAST:event_tableComboBoxItemStateChanged

    private void emptyLogButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptyLogButtonActionPerformed
    {//GEN-HEADEREND:event_emptyLogButtonActionPerformed
		if (Main.table(LOG).rowCount() == 0)
		{
			int opt = rowOperationOptionPane.showConfirmDialog(null, "Log is already empty.","WARNING", rowOperationOptionPane.OK_CANCEL_OPTION);
		}
		else
		{
			int opt = rowOperationOptionPane.showConfirmDialog(null, "Are you sure you want to empty the log? This action cannot be undone!","WARNING", rowOperationOptionPane.YES_NO_OPTION);

			if (opt == rowOperationOptionPane.YES_OPTION)
			{
				try
				{
					Main.statement = Main.connection().prepareStatement("DELETE FROM log");
					int deleteCount = Main.statement.executeUpdate();

					Main.table(LOG).setRowCount(insertIntoTableFromMain(logTable, Main.table(LOG)));
					logCountJabel.setText("Entries in log: 0");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
    }//GEN-LAST:event_emptyLogButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editButtonActionPerformed
    {//GEN-HEADEREND:event_editButtonActionPerformed
		if (mainTable.getSelectedRow() != -1)
		{
			editDialogAction = EDITING;		
			initEditPanel();
		}
		else rowOperationOptionPane.showConfirmDialog(null, "Please select a row to edit first.","WARNING", rowOperationOptionPane.OK_CANCEL_OPTION);
		
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveChangesButtonActionPerformed
    {//GEN-HEADEREND:event_saveChangesButtonActionPerformed
        switch(editDialogAction)
		{
			case(EDITING):
			{
				update();
			}
			break;
			case(INSERTING):
			{
				insert();
			}
			break;
		}
		
		editDialog.dispose();
    }//GEN-LAST:event_saveChangesButtonActionPerformed

    private void branchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_branchButtonActionPerformed
    {//GEN-HEADEREND:event_branchButtonActionPerformed
        selectedBranch = branchComboBox.getSelectedIndex();
		tripsPerBranch();
    }//GEN-LAST:event_branchButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_insertButtonActionPerformed
    {//GEN-HEADEREND:event_insertButtonActionPerformed
        if (currentTable != NONE)
		{
			editDialogAction = INSERTING;
			initEditPanel();
		}
		else rowOperationOptionPane.showConfirmDialog(null, "Please select a table to insert into first.","WARNING", rowOperationOptionPane.OK_CANCEL_OPTION);
    }//GEN-LAST:event_insertButtonActionPerformed

    private void cancelChangesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelChangesButtonActionPerformed
    {//GEN-HEADEREND:event_cancelChangesButtonActionPerformed
        editDialog.dispose();
    }//GEN-LAST:event_cancelChangesButtonActionPerformed

    private void branchButtonSelectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_branchButtonSelectActionPerformed
    {//GEN-HEADEREND:event_branchButtonSelectActionPerformed
		datetime_branch_start.setDatetime("2000", "01", "01", "00", "00", "00");
		datetime_branch_end.setDatetime("2050", "12", "31", "23", "59", "59");
    }//GEN-LAST:event_branchButtonSelectActionPerformed

    private void branchButtonEmptyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_branchButtonEmptyActionPerformed
    {//GEN-HEADEREND:event_branchButtonEmptyActionPerformed
        datetime_branch_start.setDatetime("-", "-", "-", "-", "-", "-");
		datetime_branch_end.setDatetime("-", "-", "-", "-", "-", "-");
    }//GEN-LAST:event_branchButtonEmptyActionPerformed

    private void brinfoComboBoxItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_brinfoComboBoxItemStateChanged
    {//GEN-HEADEREND:event_brinfoComboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
		{	
			if ((brinfoComboBox.getSelectedIndex() == 0))
			{
				brinfoEmployeeJabel.setText("Number of employees: 0");
				brinfoSalarJabel.setText("Total salary: 0.0");
				//brinfoReservationJabel.setText("Number of reservations: 0");
				//brinfoProfitJabel.setText("Profit from trips: 0.0");
				
				emptyTable(brinfoEmployeeTable, 9, 4, tableWidth);
				emptyTable(brinfoManagerTable, 2, 4, tableWidth);
				emptyTable(brinfoTable, 2, 4, tableWidth);
			}
			else
			{
				branchInfo();
			}
		}
    }//GEN-LAST:event_brinfoComboBoxItemStateChanged

    private void transferWorkerButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_transferWorkerButtonActionPerformed
    {//GEN-HEADEREND:event_transferWorkerButtonActionPerformed
        int row = addWorkerTable.getSelectedRow();
		
		if (row < 0)
		{
			throwError("Please select a worker to transfer!");
			return;
		}
		
		//Clear previous values
		//=========================================================================
			add_it_username.setText("");
			add_it_password.setText("");
			datetime_add_it.resetDatetime();
		//=========================================================================
		
		//Load data into the fields
		//=========================================================================
		add_it_at.setText(String.valueOf(addWorkerTable.getValueAt(row, 0)));
		add_it_name.setText(String.valueOf(addWorkerTable.getValueAt(row, 1)));
		add_it_lname.setText(String.valueOf(addWorkerTable.getValueAt(row, 2)));
		add_it_salary.setText(String.valueOf(addWorkerTable.getValueAt(row, 3)));
		add_it_branch.setSelectedItem(String.valueOf(addWorkerTable.getValueAt(row, 4)));
		
		add_it_at.setEnabled(false);
		add_it_name.setEnabled(false);
		add_it_lname.setEnabled(false);
		add_it_salary.setEnabled(false);
		add_it_branch.setEnabled(false);
		//=========================================================================
		
		addItWorkerDialog.setVisible(true);
    }//GEN-LAST:event_transferWorkerButtonActionPerformed

    private void add_it_nameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_add_it_nameActionPerformed
    {//GEN-HEADEREND:event_add_it_nameActionPerformed
       
    }//GEN-LAST:event_add_it_nameActionPerformed

    private void saveChangesButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveChangesButton1ActionPerformed
    {//GEN-HEADEREND:event_saveChangesButton1ActionPerformed
		try
		{	
			//Parse values
			//===============================================================================
			String at = add_it_at.getText();
			String name = add_it_name.getText();
			String lname = add_it_lname.getText();
			String salaryStr = add_it_salary.getText();
			String branchStr = String.valueOf(add_it_branch.getSelectedItem());
			String username = add_it_username.getText();
			String password = add_it_password.getText();

			if (at.equals(""))
				at = null;

			if (name.equals(""))
				name = null;

			if (lname.equals(""))
				lname = null;

			if (salaryStr.equals(""))
				salaryStr = null;

			if (username.equals(""))
				username = null;

			if (password.equals(""))
				password = null;

			Float salary;
			Integer branch;

			if (salaryStr == null)
				salary = null;
			else salary = Float.valueOf(salaryStr);

			if (branchStr == null)
				branch = null;
			else branch = Integer.valueOf(branchStr);

			LocalDateTime date = datetime_add_it.toDatetime();

			//===============================================================================

			//Execute queries
			//===============================================================================

			if (add_it_at.isEnabled()) //Adding new worker
			{
				//Add to worker
				LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
				
				values.add(new Pair<Object, Integer>(at, Types.CHAR));
				values.add(new Pair<Object, Integer>(name, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(lname, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(salary, Types.REAL));
				values.add(new Pair<Object, Integer>(branch, Types.INTEGER));
				
				DatabaseQuery query = new DatabaseQuery("INSERT INTO worker VALUES (?,?,?,?,?)", values);
				query.executeUpdate();
				
				//Add to it worker
				values = new LinkedList<Pair<Object, Integer>>();
				
				values.add(new Pair<Object, Integer>(at, Types.CHAR));
				values.add(new Pair<Object, Integer>(username, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(password, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(date, Types.TIMESTAMP));
				values.add(new Pair<Object, Integer>(null, Types.TIMESTAMP));
				
				query = new DatabaseQuery("INSERT INTO it_worker VALUES (?,?,?,?,?)", values);
				query.executeUpdate();
			}
			else //Transfering existing worker
			{
				//Add to it worker
				LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
				
				values.add(new Pair<Object, Integer>(at, Types.CHAR));
				values.add(new Pair<Object, Integer>(username, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(password, Types.VARCHAR));
				values.add(new Pair<Object, Integer>(date, Types.TIMESTAMP));
				values.add(new Pair<Object, Integer>(null, Types.TIMESTAMP));
				
				DatabaseQuery query = new DatabaseQuery("INSERT INTO it_worker VALUES (?,?,?,?,?)", values);
				query.executeUpdate();
			}

			//===============================================================================

			addItWorkerDialog.dispose();
			
			//Update tables
			addItWorkerPanel();
		}
		catch (DateTimeParseException e)
		{
			String msg = e.getMessage();
			
			if (msg.contains("Invalid date"))
			{
				addItWorkerDialog.dispose();
				throwError("The given date does not exist!");
			}
			else
			{
				addItWorkerDialog.dispose();
				throwError("You need to either set all date fields to NULL or give a complete date!");
			}
		}
		catch (NumberFormatException e)
		{
			addItWorkerDialog.dispose();
			throwError("Text is not allowed in numeric fields!");
		}
		catch (SQLIntegrityConstraintViolationException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("23000"))
			{
				if (msg.contains("Duplicate entry"))
				{
					addItWorkerDialog.dispose();
					throwError("There is already an entry with that key.");
				}
				else
				{
					addItWorkerDialog.dispose();
					throwError(e.getMessage());
				}
			}
			
		}
		catch(MysqlDataTruncation e)
		{
			addItWorkerDialog.dispose();
			throwError(e.getMessage());
		}
		catch(SQLException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("45000")) //User defined error (see triggers)
			{
				addItWorkerDialog.dispose();
				throwError(msg);
			}
			else
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
    }//GEN-LAST:event_saveChangesButton1ActionPerformed

    private void cancelChangesButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelChangesButton1ActionPerformed
    {//GEN-HEADEREND:event_cancelChangesButton1ActionPerformed
        addItWorkerDialog.dispose();
    }//GEN-LAST:event_cancelChangesButton1ActionPerformed

    private void addNewItWorkerButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addNewItWorkerButtonActionPerformed
    {//GEN-HEADEREND:event_addNewItWorkerButtonActionPerformed
		add_it_at.setEnabled(true);
		add_it_name.setEnabled(true);
		add_it_lname.setEnabled(true);
		add_it_salary.setEnabled(true);
		add_it_branch.setEnabled(true);
		
		add_it_at.setText("");
		add_it_name.setText("");
		add_it_lname.setText("");
		add_it_salary.setText("");
		add_it_branch.setSelectedIndex(0);
		
		add_it_username.setText("");
		add_it_password.setText("");
		datetime_add_it.resetDatetime();
		
		addItWorkerDialog.setVisible(true);
    }//GEN-LAST:event_addNewItWorkerButtonActionPerformed

    private void profitComboBoxItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_profitComboBoxItemStateChanged
    {//GEN-HEADEREND:event_profitComboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED)
		{	
			if ((profitComboBox.getSelectedIndex() == 0))
			{
				expenseEmployeeJabel.setText("Number of Employees: 0");
				expensesJabel.setText("Total Expenses: 0.0");
				profitJabel.setText("Profit: 0.0");
				incomeJabel.setText("Total Income: 0.0");
				reservationJabel.setText("Number of Reservations: 0");
				
				emptyTable(profitTable, 11, 4, tableWidth);
				emptyTable(expensesTable, 11, 2, tableWidth);
			}
			else
			{
				profit();
			}
		}
    }//GEN-LAST:event_profitComboBoxItemStateChanged

    private void resoff_run_buttonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_resoff_run_buttonActionPerformed
    {//GEN-HEADEREND:event_resoff_run_buttonActionPerformed
		LinkedList<Pair<Object, Integer>> input = new LinkedList<Pair<Object, Integer>>();
		
		input.add(new Pair<Object, Integer>(resoff_lname_field.getText(), Types.VARCHAR));
		
		DatabaseCall call = new DatabaseCall("resoff_from_lname", input);
		insertIntoTableFromCall(resoffTable, call);
    }//GEN-LAST:event_resoff_run_buttonActionPerformed

	public static void mainWindow()
	{
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainWindow().setVisible(true);
			}
		});
	}
	
	private void emptyTable(JTable tab, int rowCount, int columnCount, int width)
	{	
		((DefaultTableModel)tab.getModel()).getDataVector().removeAllElements();
		((DefaultTableModel)tab.getModel()).setRowCount(rowCount);
		((DefaultTableModel)tab.getModel()).setColumnCount(columnCount);

		for (int i = 0; i < columnCount; i++)
		{
			tab.getColumnModel().getColumn(i).setPreferredWidth(width/columnCount);
			tab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(" ");
		}

		tab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		tab.setEnabled(false);
	}
	
	//Updates the selected table in the "Show Tables" tab with the most recent data.
	//Gets called twice, after list or combo box change
	private void updateCurrentTable() throws SQLException
	{
		currentTable = tableComboBox.getSelectedIndex() - 1;
		
		Main.table(currentTable).setRowCount(insertIntoTableFromMain(mainTable, Main.table(currentTable)));
		mainTable.setEnabled(true);
		rowCountJabel.setText("Number of table entries: " + Main.table(currentTable).rowCount());
	}
	
	private int insertIntoTableFromQuery(javax.swing.JTable jTab, DatabaseQuery  query) throws SQLException
	{		
		int rowCount = 0;
		int columnCount = 0;
		int maxwidth = 14;

		query.execute();
		
		ResultSetMetaData md = Main.res.getMetaData();
		
		columnCount = md.getColumnCount();
	
		((DefaultTableModel)jTab.getModel()).setRowCount(0); //Clear table
		((DefaultTableModel)jTab.getModel()).setColumnCount(columnCount);
		
		//===========================================================================

		int[] maxLengthPerColumn = new int[columnCount];
		
		jTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		if (!Main.res.next())
		{
			for (int i = 0; i < columnCount; ++i)
			{
				jTab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(md.getColumnLabel(i+1));
				maxLengthPerColumn[i] = md.getColumnLabel(i+1).length();
				jTab.getColumnModel().getColumn(i).setPreferredWidth(maxLengthPerColumn[i]*12);
			}
		}
		else
		{
			do
			{
				((DefaultTableModel)jTab.getModel()).setRowCount(rowCount+1);
				
				for (int i = 0; i < columnCount; ++i)
				{
					jTab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(md.getColumnLabel(i+1));

					if (maxLengthPerColumn[i] == 0)
						maxLengthPerColumn[i] = md.getColumnLabel(i+1).length();

					if (Main.res.getObject(i+1) != null)
					{
						jTab.setValueAt(Main.res.getObject(i+1), rowCount, i);
					}
					else jTab.setValueAt("NULL", rowCount, i);

					String str = Main.res.getString(i+1);
					if (str == null) str = "null";

					if (str.length() > maxLengthPerColumn[i])
						maxLengthPerColumn[i] = Main.res.getString(i+1).length();
					
					if (maxLengthPerColumn[i] > maxwidth)
						maxLengthPerColumn[i] = maxwidth;

					jTab.getColumnModel().getColumn(i).setPreferredWidth(maxLengthPerColumn[i]*12);
				}

				rowCount++;
			}
			while(Main.res.next());
		}

		if (jTab.getPreferredSize().width < tableWidth)
			jTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		//jTab.setEnabled(true);
		
		return rowCount;
	}
	
	private int insertIntoTableFromMain(javax.swing.JTable jTab, DatabaseTable dbTab) throws SQLException
	{		
		int rowCount = 0;
		int columnCount = dbTab.columnCount();
		int maxwidth = 14;
		
		//Execute query
		//===========================================================================

		Main.statement = Main.connection().prepareStatement(dbTab.query());
		Main.res = Main.statement.executeQuery();
				
		((DefaultTableModel)jTab.getModel()).setRowCount(0); //Clear table
		((DefaultTableModel)jTab.getModel()).setColumnCount(columnCount);
		
		//===========================================================================

		int[] maxLengthPerColumn = new int[columnCount];
		
		jTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		if (!Main.res.next())
		{
			for (int i = 0; i < columnCount; ++i)
			{
				jTab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(dbTab.columnAlias(i));
				maxLengthPerColumn[i] = dbTab.columnAlias(i).length();
				jTab.getColumnModel().getColumn(i).setPreferredWidth(maxLengthPerColumn[i]*12);
			}	
		}
		else
		{
			do
			{
				((DefaultTableModel)jTab.getModel()).setRowCount(rowCount+1);
				
				for (int i = 0; i < columnCount; ++i)
				{
					jTab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(dbTab.columnAlias(i));

					if (maxLengthPerColumn[i] == 0)
						maxLengthPerColumn[i] = dbTab.columnAlias(i).length();

					if (Main.res.getObject(i+1) != null)
					{
						jTab.setValueAt(Main.res.getObject(i+1), rowCount, i);
					}
					else jTab.setValueAt("NULL", rowCount, i);

					String str = Main.res.getString(i+1);
					if (str == null) str = "null";

					if (str.length() > maxLengthPerColumn[i])
						maxLengthPerColumn[i] = Main.res.getString(i+1).length();
					
					if (maxLengthPerColumn[i] > maxwidth)
						maxLengthPerColumn[i] = maxwidth;

					jTab.getColumnModel().getColumn(i).setPreferredWidth(maxLengthPerColumn[i]*12);
				}

				rowCount++;
			}
			while(Main.res.next());
		}

		if (jTab.getPreferredSize().width < tableWidth)
			jTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		//jTab.setEnabled(true);
		
		return rowCount;
	}
	
	private int insertIntoTableFromCall(javax.swing.JTable jTab, DatabaseCall call)
	{
		int rowCount = 0;
		int columnCount = 0;
		int maxwidth = 14;
		
		try
		{
			call.execute();

			ResultSetMetaData md = Main.res.getMetaData();

			columnCount = md.getColumnCount();

			((DefaultTableModel)jTab.getModel()).setRowCount(1);
			((DefaultTableModel)jTab.getModel()).setColumnCount(columnCount);

			//===========================================================================

			int[] maxLengthPerColumn = new int[md.getColumnCount()];

			jTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
			if (!Main.res.next()) //Check for empty result set
			{
				((DefaultTableModel)jTab.getModel()).setRowCount(0);
				((DefaultTableModel)jTab.getModel()).setColumnCount(1);

				jTab.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("NO RESULTS");
				jTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			}
			else
			{
				do
				{
					do
					{
						((DefaultTableModel)jTab.getModel()).setRowCount(rowCount+1);

						for (int i = 0; i < columnCount; ++i)
						{
							jTab.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(md.getColumnLabel(i+1));

							if (maxLengthPerColumn[i] == 0)
								maxLengthPerColumn[i] = md.getColumnLabel(i+1).length();

							if (Main.res.getObject(i+1) != null)
							{
								jTab.setValueAt(Main.res.getObject(i+1), rowCount, i);
							}
							else jTab.setValueAt("NULL", rowCount, i);

							String str = Main.res.getString(i+1);
							if (str == null) str = "null";

							if (str.length() > maxLengthPerColumn[i])
								maxLengthPerColumn[i] = Main.res.getString(i+1).length();

							if (maxLengthPerColumn[i] > maxwidth)
								maxLengthPerColumn[i] = maxwidth;

							jTab.getColumnModel().getColumn(i).setPreferredWidth(maxLengthPerColumn[i]*12);
						}

						rowCount++;
					}
					while(Main.res.next());

					if (Main.call.getMoreResults())
					{
						Main.res = Main.call.getResultSet();
						Main.res.next();
					}
					else break;
				}
				while(true);
			}
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
			
			((DefaultTableModel)jTab.getModel()).setRowCount(0);
			((DefaultTableModel)jTab.getModel()).setColumnCount(1);

			jTab.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("NO RESULTS");
			jTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			
			rowCount = 0;
		}
		finally
		{
			if (jTab.getPreferredSize().width < tableWidth)
				jTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		}
		
		return rowCount;
	}
	
	private void setupComboBoxFromQuery(javax.swing.JComboBox<String> jCombo, DatabaseQuery query)
	{
		try
		{
			query.execute();
			
			while(Main.res.next())
			{
				jCombo.addItem(Main.res.getString(1));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void initEditPanel()
	{
		if (currentEditPanel != null)
			currentEditPanel.setVisible(false);
		
		switch(currentTable)
		{
			case ADMIN: currentEditPanel = editAdminPanel; break;
			case BRANCH:  currentEditPanel = editBranchPanel; break;
			case DESTINATION: currentEditPanel = editDestinationPanel; break;
			case DRIVER: currentEditPanel = editDriverPanel; break;
			case EVENT: currentEditPanel = editEventPanel; break;
			case GUIDE: currentEditPanel = editGuidePanel; break;
			case IT_WORKER: currentEditPanel = editItWorkerPanel; break;
			case LANGUAGES: currentEditPanel = editLanguagesPanel;  break;
			case LOG: currentEditPanel = editLogPanel;  break;
			case MANAGES: currentEditPanel = editManagesPanel;  break;
			case OFFER: currentEditPanel = editOfferPanel;  break;
			case PHONES: currentEditPanel = editPhonesPanel;  break;
			case RESERVATION: currentEditPanel = editReservationPanel;  break;
			case RESERVATION_OFFER: currentEditPanel = editReservationOfferPanel;  break;
			case TRAVEL_TO: currentEditPanel = editTravelToPanel;  break;
			case TRIP: currentEditPanel = editTripPanel; break;
			case WORKER: currentEditPanel = editWorkerPanel;  break;
		}
		
		setupComboBoxes();
		
		switch (editDialogAction)
		{
			case EDITING:
			{
				Main.table(currentTable).setValues();
				editDialog.setTitle("Edit");
				editJabel.setText("Edit");
				saveChangesButton.setText("Save changes");
			}
			break;
			
			case INSERTING:
			{
				Main.table(currentTable).resetValues();
				editDialog.setTitle("Insert");
				editJabel.setText("Insert");
				saveChangesButton.setText("Add values");
			}
			break;
		}
		
		if (currentEditPanel != null)
			currentEditPanel.setVisible(true);
		
		editDialog.setVisible(true);
	}
	
	/*private String getDatetime(DatetimeFields date)
	{
		String str = "";
		
		str += date.year.getSelectedItem().toString() + "-";
		str += date.month.getSelectedItem().toString() + "-";
		str += date.day.getSelectedItem().toString() + " ";
		str += date.hours.getSelectedItem().toString() + ":";
		str += date.minutes.getSelectedItem().toString() + ":";
		str += date.seconds.getSelectedItem().toString();
		
		return str;
	}*/
	
	private void update()
	{	
		//Prepare query
		//====================================================================================================
		String query = "UPDATE " + Main.table(currentTable).name() + " SET ";
		
		int columnCount = Main.table(currentTable).columnCount();
		
		for (int i = 0; i < columnCount; ++i)
		{
			query += Main.table(currentTable).columnName(i) + " = ?";
			
			if (i < columnCount - 1)
				query += ", ";
		}
		
		query += " " + Main.table(currentTable).where();
		
		//System.out.println(query + "\n");
		//====================================================================================================
		
		
		//Set values
		//====================================================================================================
		
		try
		{
			Main.statement = Main.connection().prepareStatement(query);

			//All the fields in SET
			for (int i = 0; i < columnCount; ++i)
			{
				Object obj = Main.table(currentTable).editFieldValue(i);
				int type = Main.table(currentTable).columnType(i);
				
				//if (obj != null)
					//System.out.printf("%s = %s (%s)\n", Main.table(currentTable).columnName(i), obj.toString(), obj.getClass().getSimpleName());
				//else System.out.printf("%s = NULL\n", Main.table(currentTable).columnName(i));

				Main.statement.setObject(i+1, obj, type);
			}
			
			//System.out.println();
			
			//All the keys in WHERE
			LinkedList<Integer> keys = Main.table(currentTable).keys();
			
			int setObjectIndex = columnCount + 1;
			int i = 0;
			for (Integer p : keys)
			{
				Object obj = Main.table(currentTable).keyValue(i);
				
				//System.out.printf("%s = %s (%s)\n", Main.table(currentTable).columnName(p), obj.toString(), obj.getClass().getSimpleName());
				
				Main.statement.setObject(setObjectIndex, obj);
				i++;
				setObjectIndex++;
			}
			
			//System.out.println("==========================================\n");
			
			Main.statement.executeUpdate();
			updateCurrentTable();
		}
		//Exceptions
		//====================================================================================================
		catch (DateTimeParseException e)
		{
			String msg = e.getMessage();
			
			if (msg.contains("Invalid date"))
			{
				editDialog.dispose();
				throwError("The given date does not exist!");
			}
			else
			{
				editDialog.dispose();
				throwError("You need to either set all date fields to NULL or give a complete date!");
			}
		}
		catch (NumberFormatException e)
		{
			editDialog.dispose();
			throwError("Text is not allowed in numeric fields!");
		}
		catch (SQLIntegrityConstraintViolationException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("23000"))
			{
				if (msg.contains("Duplicate entry"))
				{
					editDialog.dispose();
					throwError("There is already an entry with that key.");
				}
				else
				{
					editDialog.dispose();
					throwError(e.getMessage());
				}
			}
			
		}
		catch(MysqlDataTruncation e)
		{
			editDialog.dispose();
			throwError(e.getMessage());
		}
		catch(SQLException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("45000")) //User defined error (see triggers)
			{
				editDialog.dispose();
				throwError(msg);
			}
			else
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		//====================================================================================================
	}
	
	private void insert()
	{
		//Prepare query
		//====================================================================================================
		String query = "INSERT INTO " + Main.table(currentTable).name() + " VALUES(";
		
		int columnCount = Main.table(currentTable).columnCount();
		
		for (int i = 0; i < columnCount; ++i)
		{
			query += "?";
			
			if (i < columnCount - 1)
				query += ", ";
		}
		
		query += ")";
		
		//System.out.println(query + "\n");
		//====================================================================================================
		
		
		//Set values
		//====================================================================================================
		
		try
		{
			Main.statement = Main.connection().prepareStatement(query);

			//All the fields in SET
			for (int i = 0; i < columnCount; ++i)
			{
				Object obj = Main.table(currentTable).editFieldValue(i);
				int type = Main.table(currentTable).columnType(i);
				
				//if (obj != null)
					//System.out.printf("%s = %s (%s)\n", Main.table(currentTable).columnName(i), obj.toString(), obj.getClass().getSimpleName());
				//else System.out.printf("%s = NULL\n", Main.table(currentTable).columnName(i));

				Main.statement.setObject(i+1, obj, type);
			}
			
			//System.out.println("==========================================\n");
			
			Main.statement.executeUpdate();
			updateCurrentTable();
		}
		//Exceptions
		//====================================================================================================
		catch (DateTimeParseException e)
		{
			String msg = e.getMessage();
			
			if (msg.contains("Invalid date"))
			{
				editDialog.dispose();
				throwError("The given date does not exist!");
			}
			else
			{
				editDialog.dispose();
				throwError("You need to either set all date fields to NULL or give a complete date!");
			}
		}
		catch (NumberFormatException e)
		{
			editDialog.dispose();
			throwError("Text is not allowed in numeric fields!");
		}
		catch (SQLIntegrityConstraintViolationException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("23000"))
			{
				if (msg.contains("Duplicate entry"))
				{
					editDialog.dispose();
					throwError("There is already an entry with that key.");
				}
				else
				{
					editDialog.dispose();
					throwError(e.getMessage());
				}
			}
			
		}
		catch(MysqlDataTruncation e)
		{
			editDialog.dispose();
			throwError(e.getMessage());
		}
		catch(SQLException e)
		{
			String sqlState = e.getSQLState();
			String msg = e.getMessage();
			
			if (sqlState.equals("45000")) //User defined error (see triggers)
			{
				editDialog.dispose();
				throwError(msg);
			}
			else
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		//====================================================================================================
	}
		
	private void setupComboBoxes()
	{
		switch(currentTable)
		{
			case ADMIN:
			{
				field_adm_AT.removeAllItems();
				setupComboBoxFromQuery(field_adm_AT, new DatabaseQuery("select wrk_AT from worker order by wrk_AT asc"));
			}
			break;
			
			case DESTINATION:
			{
				field_dst_location.removeAllItems();
				field_dst_location.addItem("-");
				setupComboBoxFromQuery(field_dst_location, new DatabaseQuery("select dst_id from destination order by dst_id asc"));
			}
			break;
			
			case DRIVER:
			{
				field_drv_AT.removeAllItems();
				setupComboBoxFromQuery(field_drv_AT, new DatabaseQuery("select wrk_AT from worker order by wrk_AT asc"));
			}
			break;
			
			case EVENT:
			{
				field_ev_tr_id.removeAllItems();
				setupComboBoxFromQuery(field_ev_tr_id, new DatabaseQuery("select tr_id from trip order by tr_id asc"));
			}
			break;
			
			case GUIDE:
			{
				field_gui_AT.removeAllItems();
				setupComboBoxFromQuery(field_gui_AT, new DatabaseQuery("select wrk_AT from worker order by wrk_AT asc"));
			}
			break;
			
			case IT_WORKER:
			{
				field_it_AT.removeAllItems();
				setupComboBoxFromQuery(field_it_AT, new DatabaseQuery("select wrk_AT from worker order by wrk_AT asc"));
			}
			break;
			
			case LANGUAGES:
			{
				field_lng_gui_AT.removeAllItems();
				setupComboBoxFromQuery(field_lng_gui_AT, new DatabaseQuery("select gui_AT from guide order by gui_AT asc"));
			}
			break;
			
			case LOG:
			{
				field_log_it_AT.removeAllItems();
				setupComboBoxFromQuery(field_log_it_AT, new DatabaseQuery("select it_AT from it_worker order by it_AT asc"));
			}
			break;
			
			case MANAGES:
			{
				field_mng_br_code.removeAllItems();
				setupComboBoxFromQuery(field_mng_br_code, new DatabaseQuery("select br_code from branch order by br_code asc"));
				
				field_mng_adm_AT.removeAllItems();
				setupComboBoxFromQuery(field_mng_adm_AT, new DatabaseQuery("select adm_AT from admin order by adm_AT asc"));
			}
			break;
			
			case OFFER:
			{
				field_off_destination.removeAllItems();
				setupComboBoxFromQuery(field_off_destination, new DatabaseQuery("select dst_id from destination order by dst_id asc"));
			}
			break;
			
			case PHONES:
			{
				field_ph_br_code.removeAllItems();
				setupComboBoxFromQuery(field_ph_br_code, new DatabaseQuery("select br_code from branch order by br_code asc"));
			}
			break;
			
			case TRIP:
			{
				field_tr_br_code.removeAllItems();
				field_tr_br_code.addItem("-");
				setupComboBoxFromQuery(field_tr_br_code, new DatabaseQuery("select br_code from branch order by br_code asc"));
				
				field_tr_drv_AT.removeAllItems();
				field_tr_drv_AT.addItem("-");
				setupComboBoxFromQuery(field_tr_drv_AT, new DatabaseQuery("select drv_AT from driver order by drv_AT asc"));
				
				field_tr_gui_AT.removeAllItems();
				field_tr_gui_AT.addItem("-");
				setupComboBoxFromQuery(field_tr_gui_AT, new DatabaseQuery("select gui_AT from guide order by gui_AT asc"));
			}
			break;
			
			case TRAVEL_TO:
			{
				field_to_dst_id.removeAllItems();
				setupComboBoxFromQuery(field_to_dst_id, new DatabaseQuery("select dst_id from destination order by dst_id asc"));
				
				field_to_tr_id.removeAllItems();
				setupComboBoxFromQuery(field_to_tr_id, new DatabaseQuery("select tr_id from trip order by tr_id asc"));
			}
			break;
			
			case RESERVATION:
			{
				field_res_tr_id.removeAllItems();
				setupComboBoxFromQuery(field_res_tr_id, new DatabaseQuery("select tr_id from trip order by tr_id asc"));
			}
			break;
			
			case RESERVATION_OFFER:
			{
				field_resoff_off_id.removeAllItems();
				setupComboBoxFromQuery(field_resoff_off_id, new DatabaseQuery("select off_id from offer order by off_id asc"));
			}
			break;
			
			case WORKER:
			{
				field_wrk_br_code.removeAllItems();
				setupComboBoxFromQuery(field_wrk_br_code, new DatabaseQuery("select br_code from branch order by br_code asc"));
			}
			break;
		}
	}
	
	private void throwError(String msg)
	{
		int opt = errorOptionPane.OK_CANCEL_OPTION;
		errorOptionPane.showConfirmDialog(null, msg, "ERROR", opt);
		errorDialog.dispose();
	}
	
	private void setupTables()
	{
		DatabaseTable.mainTable = mainTable;
		
		List<Object> temp = new ArrayList<Object>();
		
		temp.add(field_adm_AT);
		temp.add(field_adm_type);
		temp.add(field_adm_diploma);
		
		Main.table(ADMIN).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_br_code);
		temp.add(field_br_street);
		temp.add(field_br_num);
		temp.add(field_br_city);
		
		Main.table(BRANCH).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_dst_id);
		temp.add(field_dst_name);
		temp.add(field_dst_descr);
		temp.add(field_dst_rtype);
		temp.add(field_dst_language);
		temp.add(field_dst_location);
		
		Main.table(DESTINATION).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_drv_AT);
		temp.add(field_drv_license);
		temp.add(field_drv_route);
		temp.add(field_drv_experience);
		
		Main.table(DRIVER).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_ev_tr_id);
		temp.add(datetime_field_ev_start);
		temp.add(datetime_field_ev_end);
		temp.add(field_ev_descr);
		
		Main.table(EVENT).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_gui_AT);
		temp.add(field_gui_cv);
		
		Main.table(GUIDE).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_it_AT);
		temp.add(field_it_username);
		temp.add(field_it_password);
		temp.add(datetime_field_it_start_date);
		temp.add(datetime_field_it_end_date);
		
		Main.table(IT_WORKER).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_lng_gui_AT);
		temp.add(field_lng_language);
		
		Main.table(LANGUAGES).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_log_entry_id);
		temp.add(field_log_it_AT);
		temp.add(field_log_table);
		temp.add(field_log_action);
		temp.add(datetime_field_log_time);
		
		Main.table(LOG).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_mng_adm_AT);
		temp.add(field_mng_br_code);
		
		Main.table(MANAGES).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_off_id);
		temp.add(field_off_destination);
		temp.add(datetime_field_off_start);
		temp.add(datetime_field_off_end);
		temp.add(field_off_cost);
		
		Main.table(OFFER).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_ph_br_code);
		temp.add(field_ph_number);
		
		Main.table(PHONES).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_res_tr_id);
		temp.add(field_res_seatnum);
		temp.add(field_res_name);
		temp.add(field_res_lname);
		temp.add(field_res_isadult);
		
		Main.table(RESERVATION).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_resoff_id);
		temp.add(field_resoff_off_id);
		temp.add(field_resoff_name);
		temp.add(field_resoff_lname);
		temp.add(field_resoff_deposit);
		
		Main.table(RESERVATION_OFFER).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_to_tr_id);
		temp.add(field_to_dst_id);
		temp.add(datetime_field_to_arrival);
		temp.add(datetime_field_to_departure);
		
		Main.table(TRAVEL_TO).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_tr_id);
		temp.add(datetime_field_tr_departure);
		temp.add(datetime_field_tr_return);
		temp.add(field_tr_maxseats);
		temp.add(field_tr_cost);
		temp.add(field_tr_br_code);
		temp.add(field_tr_gui_AT);
		temp.add(field_tr_drv_AT);
		
		Main.table(TRIP).setEditFields(temp);
		
		temp = new ArrayList<Object>();
		
		temp.add(field_wrk_AT);
		temp.add(field_wrk_name);
		temp.add(field_wrk_lname);
		temp.add(field_wrk_salary);
		temp.add(field_wrk_br_code);
		
		Main.table(WORKER).setEditFields(temp);
	}
	
	private void tripsPerBranch()
	{
		try
		{
			LinkedList<Pair<Object, Integer>> input = new LinkedList<Pair<Object, Integer>>();
			input.add(new Pair<Object, Integer>(Integer.valueOf(branchComboBox.getSelectedItem().toString()), Types.INTEGER));
			input.add(new Pair<Object, Integer>(datetime_branch_start.toDatetime(), Types.TIMESTAMP));
			input.add(new Pair<Object, Integer>(datetime_branch_end.toDatetime(), Types.TIMESTAMP));

			DatabaseCall call = new DatabaseCall("show_trips", input, null);

			int rowCount = insertIntoTableFromCall(branchTable, call);
			
			branchRowCountJabel.setText("Number of table entries: " + rowCount);
		}
		catch (DateTimeParseException e)
		{
			String msg = e.getMessage();

			if (msg.contains("Invalid date"))
			{
				editDialog.dispose();
				throwError("The given date does not exist!");
			}
			else
			{
				editDialog.dispose();
				throwError("You need to either set all date fields to NULL or give a complete date!");
			}
		}
	}
	
	private void branchInfo()
	{
		try
		{
			Integer selectedBranch = Integer.valueOf(brinfoComboBox.getSelectedItem().toString());
					
			//Employees table
			//========================================================================================
			LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
			values.add(new Pair<Object, Integer>(selectedBranch, Types.INTEGER));
			
			LinkedList<Integer> output = new LinkedList<Integer>();
			output.add(Types.REAL);

			DatabaseCall call = new DatabaseCall("show_employees", values, output);
			int rowCount = insertIntoTableFromCall(brinfoEmployeeTable, call);
			//========================================================================================
			
			//Branch info table
			//========================================================================================
			DatabaseQuery query = new DatabaseQuery
			(
					Main.table(BRANCH).labeledQuery() + " WHERE br_code = ?",
					values
			);
			
			insertIntoTableFromQuery(brinfoTable, query);
			//========================================================================================
			
			//Manager table
			//========================================================================================
			query = new DatabaseQuery
			(
					"SELECT wrk_name AS 'Name', wrk_lname AS 'Lastname' " +
					"FROM manages INNER JOIN admin ON mng_adm_AT = adm_AT " +
					"INNER JOIN worker ON adm_AT = wrk_AT " +
					"WHERE mng_br_code = ? " +
					"LIMIT 0,1 ",
					values
			);
			
			insertIntoTableFromQuery(brinfoManagerTable, query);
			//========================================================================================
			
			brinfoEmployeeJabel.setText("Number of table entries: " + rowCount);
			brinfoSalarJabel.setText("Total salary: " + Main.call.getFloat(2));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			String msg = e.getMessage();

			if (msg.contains("Invalid date"))
			{
				editDialog.dispose();
				throwError("The given date does not exist!");
			}
			else
			{
				editDialog.dispose();
				throwError("You need to either set all date fields to NULL or give a complete date!");
			}
		}
	}
	
	private void profit()
	{
		try
		{
			Integer selectedBranch = Integer.valueOf(profitComboBox.getSelectedItem().toString());
					
			//Profit table
			//========================================================================================
			LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
			values.add(new Pair<Object, Integer>(selectedBranch, Types.INTEGER));
			
			LinkedList<Integer> output = new LinkedList<Integer>();
			output.add(Types.REAL);

			DatabaseCall call = new DatabaseCall("profit", values, output);
			insertIntoTableFromCall(profitTable, call);
			
			float income = Main.call.getFloat(2);
			incomeJabel.setText("Total Income: " + Float.toString(income));
			//========================================================================================
			
			//Expenses table
			//========================================================================================
			values = new LinkedList<Pair<Object, Integer>>();
			values.add(new Pair<Object, Integer>(selectedBranch, Types.INTEGER));
			
			output = new LinkedList<Integer>();
			output.add(Types.REAL);

			call = new DatabaseCall("expenses", values, output);
			int employees = insertIntoTableFromCall(expensesTable, call);
			
			float expenses = Main.call.getFloat(2);
			expenseEmployeeJabel.setText("Number of Employees: " + Integer.toString(employees));
			expensesJabel.setText("Total Expenses: " + Float.toString(expenses));
			//========================================================================================
			
			profitJabel.setText("Profit: " + Float.toString(income - expenses));
			
			values = new LinkedList<Pair<Object, Integer>>();
			values.add(new Pair<Object, Integer>(selectedBranch, Types.INTEGER));
			
			DatabaseQuery q = new DatabaseQuery(
			"select count(*) " +
			"FROM reservation " +
			"INNER JOIN trip ON res_tr_id = tr_id " +
			"WHERE tr_br_code = ?", values);
			
			q.execute();
			Main.res.next();
			
			reservationJabel.setText("Number of Reservations: " + Integer.toString(Main.res.getInt(1)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void setModel(JTable tab)
	{
		tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        });
	}
	
	private void addItWorkerPanel()
	{
		try
		{
			insertIntoTableFromMain(addItTable, Main.table(IT_WORKER));
			insertIntoTableFromMain(addWorkerTable, Main.table(WORKER));
			
			add_it_branch.removeAllItems();
			setupComboBoxFromQuery(add_it_branch, new DatabaseQuery("select br_code from branch order by br_code asc"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final int NONE = Main.NONE,
	ADMIN = Main.ADMIN, BRANCH = Main.BRANCH, DESTINATION = Main.DESTINATION,DRIVER = Main.DRIVER,
	EVENT = Main.EVENT,GUIDE = Main.GUIDE,IT_WORKER = Main.IT_WORKER,LANGUAGES = Main.LANGUAGES,
	LOG = Main.LOG,MANAGES = Main.MANAGES,OFFER = Main.OFFER,PHONES = Main.PHONES,RESERVATION = Main.RESERVATION,
	RESERVATION_OFFER = Main.RESERVATION_OFFER,TRAVEL_TO = Main.TRAVEL_TO,TRIP = Main.TRIP,WORKER = Main.WORKER;
	
	private static final int EDITING = 0, INSERTING = 1;
	
	private int currentTable; //The selected table when the user is under the Show Tables tab
	
	private static final int tableWidth = 485;
	private static final int tableDefaultRowCount = 12;
	private static final int tableDefaultColumnCount =4;
	private javax.swing.JPanel currentPanel = null;
	private javax.swing.JPanel currentEditPanel = null;
	private int editDialogAction = EDITING;
	
	private int selectedBranch = 0;
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable addItTable;
    private javax.swing.JDialog addItWorkerDialog;
    private javax.swing.JButton addNewItWorkerButton;
    private javax.swing.JTable addWorkerTable;
    private javax.swing.JTextField add_it_at;
    private javax.swing.JComboBox<String> add_it_branch;
    private javax.swing.JComboBox<String> add_it_day;
    private javax.swing.JComboBox<String> add_it_hours;
    private javax.swing.JTextField add_it_lname;
    private javax.swing.JComboBox<String> add_it_minutes;
    private javax.swing.JComboBox<String> add_it_month;
    private javax.swing.JTextField add_it_name;
    private javax.swing.JTextField add_it_password;
    private javax.swing.JTextField add_it_salary;
    private javax.swing.JComboBox<String> add_it_seconds;
    private javax.swing.JTextField add_it_username;
    private javax.swing.JComboBox<String> add_it_year;
    private javax.swing.JLabel atJabel;
    private javax.swing.JButton branchButton;
    private javax.swing.JButton branchButtonEmpty;
    private javax.swing.JButton branchButtonSelect;
    private javax.swing.JComboBox<String> branchComboBox;
    private javax.swing.JLabel branchRowCountJabel;
    private javax.swing.JTable branchTable;
    private javax.swing.JComboBox<String> branch_end_date_day;
    private javax.swing.JComboBox<String> branch_end_date_hours;
    private javax.swing.JComboBox<String> branch_end_date_minutes;
    private javax.swing.JComboBox<String> branch_end_date_month;
    private javax.swing.JComboBox<String> branch_end_date_seconds;
    private javax.swing.JComboBox<String> branch_end_date_year;
    private javax.swing.JComboBox<String> branch_start_date_day;
    private javax.swing.JComboBox<String> branch_start_date_hours;
    private javax.swing.JComboBox<String> branch_start_date_minutes;
    private javax.swing.JComboBox<String> branch_start_date_month;
    private javax.swing.JComboBox<String> branch_start_date_seconds;
    private javax.swing.JComboBox<String> branch_start_date_year;
    private javax.swing.JComboBox<String> brinfoComboBox;
    private javax.swing.JLabel brinfoEmployeeJabel;
    private javax.swing.JTable brinfoEmployeeTable;
    private javax.swing.JTable brinfoManagerTable;
    private javax.swing.JLabel brinfoSalarJabel;
    private javax.swing.JTable brinfoTable;
    private javax.swing.JButton cancelChangesButton;
    private javax.swing.JButton cancelChangesButton1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel editAdminPanel;
    private javax.swing.JPanel editBranchPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel editDestinationPanel;
    private javax.swing.JDialog editDialog;
    private javax.swing.JPanel editDriverPanel;
    private javax.swing.JPanel editEventPanel;
    private javax.swing.JPanel editGuidePanel;
    private javax.swing.JPanel editItWorkerPanel;
    private javax.swing.JLabel editJabel;
    private javax.swing.JLabel editJabel1;
    private javax.swing.JPanel editLanguagesPanel;
    private javax.swing.JPanel editLogPanel;
    private javax.swing.JPanel editManagesPanel;
    private javax.swing.JPanel editOfferPanel;
    private javax.swing.JPanel editPhonesPanel;
    private javax.swing.JPanel editReservationOfferPanel;
    private javax.swing.JPanel editReservationPanel;
    private javax.swing.JPanel editTravelToPanel;
    private javax.swing.JPanel editTripPanel;
    private javax.swing.JPanel editWorkerPanel;
    private javax.swing.JButton emptyLogButton;
    private javax.swing.JDialog emptyLogDialog;
    private javax.swing.JOptionPane emptyLogOptionPane;
    private javax.swing.JDialog errorDialog;
    private javax.swing.JOptionPane errorOptionPane;
    private javax.swing.JLabel expenseEmployeeJabel;
    private javax.swing.JLabel expensesJabel;
    private javax.swing.JTable expensesTable;
    private javax.swing.JComboBox<String> field_adm_AT;
    private javax.swing.JTextField field_adm_diploma;
    private javax.swing.JComboBox<String> field_adm_type;
    private javax.swing.JTextField field_br_city;
    private javax.swing.JTextField field_br_code;
    private javax.swing.JTextField field_br_num;
    private javax.swing.JTextField field_br_street;
    private javax.swing.JComboBox<String> field_drv_AT;
    private javax.swing.JTextField field_drv_experience;
    private javax.swing.JComboBox<String> field_drv_license;
    private javax.swing.JComboBox<String> field_drv_route;
    private javax.swing.JTextArea field_dst_descr;
    private javax.swing.JTextField field_dst_id;
    private javax.swing.JComboBox<String> field_dst_language;
    private javax.swing.JComboBox<String> field_dst_location;
    private javax.swing.JTextField field_dst_name;
    private javax.swing.JComboBox<String> field_dst_rtype;
    private javax.swing.JTextArea field_ev_descr;
    private javax.swing.JComboBox<String> field_ev_end_day;
    private javax.swing.JComboBox<String> field_ev_end_hours;
    private javax.swing.JComboBox<String> field_ev_end_minutes;
    private javax.swing.JComboBox<String> field_ev_end_month;
    private javax.swing.JComboBox<String> field_ev_end_seconds;
    private javax.swing.JComboBox<String> field_ev_end_year;
    private javax.swing.JComboBox<String> field_ev_start_day;
    private javax.swing.JComboBox<String> field_ev_start_hours;
    private javax.swing.JComboBox<String> field_ev_start_minutes;
    private javax.swing.JComboBox<String> field_ev_start_month;
    private javax.swing.JComboBox<String> field_ev_start_seconds;
    private javax.swing.JComboBox<String> field_ev_start_year;
    private javax.swing.JComboBox<String> field_ev_tr_id;
    private javax.swing.JComboBox<String> field_gui_AT;
    private javax.swing.JTextArea field_gui_cv;
    private javax.swing.JComboBox<String> field_it_AT;
    private javax.swing.JComboBox<String> field_it_end_date_day;
    private javax.swing.JComboBox<String> field_it_end_date_hours;
    private javax.swing.JComboBox<String> field_it_end_date_minutes;
    private javax.swing.JComboBox<String> field_it_end_date_month;
    private javax.swing.JComboBox<String> field_it_end_date_seconds;
    private javax.swing.JComboBox<String> field_it_end_date_year;
    private javax.swing.JTextField field_it_password;
    private javax.swing.JComboBox<String> field_it_start_date_day;
    private javax.swing.JComboBox<String> field_it_start_date_hours;
    private javax.swing.JComboBox<String> field_it_start_date_minutes;
    private javax.swing.JComboBox<String> field_it_start_date_month;
    private javax.swing.JComboBox<String> field_it_start_date_seconds;
    private javax.swing.JComboBox<String> field_it_start_date_year;
    private javax.swing.JTextField field_it_username;
    private javax.swing.JComboBox<String> field_lng_gui_AT;
    private javax.swing.JComboBox<String> field_lng_language;
    private javax.swing.JComboBox<String> field_log_action;
    private javax.swing.JTextField field_log_entry_id;
    private javax.swing.JComboBox<String> field_log_it_AT;
    private javax.swing.JComboBox<String> field_log_table;
    private javax.swing.JComboBox<String> field_log_time_day;
    private javax.swing.JComboBox<String> field_log_time_hours;
    private javax.swing.JComboBox<String> field_log_time_minutes;
    private javax.swing.JComboBox<String> field_log_time_month;
    private javax.swing.JComboBox<String> field_log_time_seconds;
    private javax.swing.JComboBox<String> field_log_time_year;
    private javax.swing.JComboBox<String> field_mng_adm_AT;
    private javax.swing.JComboBox<String> field_mng_br_code;
    private javax.swing.JTextField field_off_cost;
    private javax.swing.JComboBox<String> field_off_destination;
    private javax.swing.JComboBox<String> field_off_end_day;
    private javax.swing.JComboBox<String> field_off_end_hours;
    private javax.swing.JComboBox<String> field_off_end_minutes;
    private javax.swing.JComboBox<String> field_off_end_month;
    private javax.swing.JComboBox<String> field_off_end_seconds;
    private javax.swing.JComboBox<String> field_off_end_year;
    private javax.swing.JTextField field_off_id;
    private javax.swing.JComboBox<String> field_off_start_day;
    private javax.swing.JComboBox<String> field_off_start_hours;
    private javax.swing.JComboBox<String> field_off_start_minutes;
    private javax.swing.JComboBox<String> field_off_start_month;
    private javax.swing.JComboBox<String> field_off_start_seconds;
    private javax.swing.JComboBox<String> field_off_start_year;
    private javax.swing.JComboBox<String> field_ph_br_code;
    private javax.swing.JTextField field_ph_number;
    private javax.swing.JComboBox<String> field_res_isadult;
    private javax.swing.JTextField field_res_lname;
    private javax.swing.JTextField field_res_name;
    private javax.swing.JTextField field_res_seatnum;
    private javax.swing.JComboBox<String> field_res_tr_id;
    private javax.swing.JTextField field_resoff_deposit;
    private javax.swing.JTextField field_resoff_id;
    private javax.swing.JTextField field_resoff_lname;
    private javax.swing.JTextField field_resoff_name;
    private javax.swing.JComboBox<String> field_resoff_off_id;
    private javax.swing.JComboBox<String> field_to_arrival_day;
    private javax.swing.JComboBox<String> field_to_arrival_hours;
    private javax.swing.JComboBox<String> field_to_arrival_minutes;
    private javax.swing.JComboBox<String> field_to_arrival_month;
    private javax.swing.JComboBox<String> field_to_arrival_seconds;
    private javax.swing.JComboBox<String> field_to_arrival_year;
    private javax.swing.JComboBox<String> field_to_departure_day;
    private javax.swing.JComboBox<String> field_to_departure_hours;
    private javax.swing.JComboBox<String> field_to_departure_minutes;
    private javax.swing.JComboBox<String> field_to_departure_month;
    private javax.swing.JComboBox<String> field_to_departure_seconds;
    private javax.swing.JComboBox<String> field_to_departure_year;
    private javax.swing.JComboBox<String> field_to_dst_id;
    private javax.swing.JComboBox<String> field_to_tr_id;
    private javax.swing.JComboBox<String> field_tr_br_code;
    private javax.swing.JTextField field_tr_cost;
    private javax.swing.JComboBox<String> field_tr_departure_day;
    private javax.swing.JComboBox<String> field_tr_departure_hours;
    private javax.swing.JComboBox<String> field_tr_departure_minutes;
    private javax.swing.JComboBox<String> field_tr_departure_month;
    private javax.swing.JComboBox<String> field_tr_departure_seconds;
    private javax.swing.JComboBox<String> field_tr_departure_year;
    private javax.swing.JComboBox<String> field_tr_drv_AT;
    private javax.swing.JComboBox<String> field_tr_gui_AT;
    private javax.swing.JTextField field_tr_id;
    private javax.swing.JTextField field_tr_maxseats;
    private javax.swing.JComboBox<String> field_tr_return_day;
    private javax.swing.JComboBox<String> field_tr_return_hours;
    private javax.swing.JComboBox<String> field_tr_return_minutes;
    private javax.swing.JComboBox<String> field_tr_return_month;
    private javax.swing.JComboBox<String> field_tr_return_seconds;
    private javax.swing.JComboBox<String> field_tr_return_year;
    private javax.swing.JTextField field_wrk_AT;
    private javax.swing.JComboBox<String> field_wrk_br_code;
    private javax.swing.JTextField field_wrk_lname;
    private javax.swing.JTextField field_wrk_name;
    private javax.swing.JTextField field_wrk_salary;
    private javax.swing.JLabel incomeJabel;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lnameJabel;
    private javax.swing.JLabel logCountJabel;
    private javax.swing.JTable logTable;
    private javax.swing.JButton logoutButton;
    private javax.swing.JDialog logoutDialog;
    private javax.swing.JOptionPane logoutOptionPane;
    private javax.swing.JList<String> mainList;
    private javax.swing.JTable mainTable;
    private javax.swing.JLabel nameJabel;
    private javax.swing.JPanel panelOption0;
    private javax.swing.JPanel panelOption1;
    private javax.swing.JPanel panelOption2;
    private javax.swing.JPanel panelOption3;
    private javax.swing.JPanel panelOption4;
    private javax.swing.JPanel panelOption5;
    private javax.swing.JPanel panelOption6;
    private javax.swing.JButton profileButton;
    private javax.swing.JDialog profileDialog;
    private javax.swing.JComboBox<String> profitComboBox;
    private javax.swing.JLabel profitJabel;
    private javax.swing.JTable profitTable;
    private javax.swing.JLabel reservationJabel;
    private javax.swing.JTable resoffTable;
    private javax.swing.JTextField resoff_lname_field;
    private javax.swing.JButton resoff_run_button;
    private javax.swing.JLabel rowCountJabel;
    private javax.swing.JDialog rowOperationDialog;
    private javax.swing.JOptionPane rowOperationOptionPane;
    private javax.swing.JButton saveChangesButton;
    private javax.swing.JButton saveChangesButton1;
    private javax.swing.JComboBox<String> tableComboBox;
    private javax.swing.JButton transferWorkerButton;
    private javax.swing.JLabel usernameJabel;
    private java.awt.Label welcomeLabel;
    // End of variables declaration//GEN-END:variables
	
	private final DatetimeFields datetime_field_ev_start;
	private final DatetimeFields datetime_field_ev_end;
	private final DatetimeFields datetime_field_tr_departure;
	private final DatetimeFields datetime_field_tr_return;
	private final DatetimeFields datetime_field_to_arrival;
	private final DatetimeFields datetime_field_to_departure;
	private final DatetimeFields datetime_field_log_time;
	private final DatetimeFields datetime_field_it_start_date;
	private final DatetimeFields datetime_field_it_end_date;
	private final DatetimeFields datetime_field_off_start;
	private final DatetimeFields datetime_field_off_end;	
	
	private final DatetimeFields datetime_branch_start;
	private final DatetimeFields datetime_branch_end;
	
	private final DatetimeFields datetime_add_it;
}