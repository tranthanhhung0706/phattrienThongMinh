package com.example.demo4.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo4.dto.Item;
import com.example.demo4.dto.MathangDTO;
import com.example.demo4.dto.OrderDTO;
import com.example.demo4.dto.Orderhistory;
import com.example.demo4.dto.TaikhoanDTO;
import com.example.demo4.entity.Banggia;
import com.example.demo4.entity.Ctpd;
import com.example.demo4.entity.CtpdId;
import com.example.demo4.entity.Danhgia;
import com.example.demo4.entity.Hoadon;
import com.example.demo4.entity.Khachhang;
import com.example.demo4.entity.Mathang;
import com.example.demo4.entity.Phieudat;
import com.example.demo4.entity.Quyen;
import com.example.demo4.entity.Size;
import com.example.demo4.entity.Taikhoan;
import com.example.demo4.repository.ChittietPDRepository;
import com.example.demo4.service.BangGiaService;
import com.example.demo4.service.CTHDService;
import com.example.demo4.service.HinhAnhService;
import com.example.demo4.service.HoaDonService;
import com.example.demo4.service.KhachhangService;
import com.example.demo4.service.MathangService;
import com.example.demo4.service.PhieuDatService;
import com.example.demo4.service.SizeService;
import com.example.demo4.service.TaikhoanService;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding.Anonymous;

@Controller
public class HomeController {
	@Autowired
	private TaikhoanService taikhoanService;
	@Autowired
	private KhachhangService khachhangService;
	@Autowired
	private MathangService mathangService;
	@Autowired
	private BangGiaService bangGiaService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private PhieuDatService phieuDatService;
	@Autowired
	private CTHDService cthdService;
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private Apriori apriori;
	@Autowired
	private ChittietPDRepository chittietPDRepository;
	@Autowired 
	private HinhAnhService hinhAnhService;
	private List<Mathang> listmhdanhgia = new ArrayList<Mathang>();

	

	@GetMapping("/")
	public String viewHomePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		System.out.print(currentPricipalName);
		if (!currentPricipalName.equals("anonymousUser")) {
			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
			System.out.print(tk.getQuyen().getMaquyen());
			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
				currentPricipalName = "ADMIN";
			} else {
				currentPricipalName = "USER";
			}
		}
		model.addAttribute("user", currentPricipalName);
		System.out.println(3);
		for(Mathang mathang : listmhdanhgia) {
			System.out.println(mathang.getMamh());
		}
		if (listmhdanhgia.size() > 0) {
			model.addAttribute("listmathang", listmhdanhgia);
		}
//		System.out.print("hung3");
		
		return "views/index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("tk", new Taikhoan());

		return "views/register";
	}

	@GetMapping("/login")
	public String showlogin(Model model) {
		return "views/login";
	}

	@PostMapping("/process_register")
	public String processRegister(Taikhoan tk) {
		String KH = "KH";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(tk.getMatkhau());
		Quyen quyen = new Quyen();
		Khachhang khachhang = new Khachhang();
		Khachhang khachhang2 = khachhangService.getkhachhangLast();
		System.out.println(khachhang2.getMakh().substring(2, 3));
		String h1 = khachhang2.getMakh().replace("KH", "");
		Integer h2 = Integer.parseInt(h1) + 1;
		System.out.println(h2);
		khachhang.setMakh(KH.concat(h2.toString()));
		khachhang.setEmail(tk.getTentk());
		khachhangService.save(khachhang);
		quyen.setMaquyen(2);
		tk.setMatkhau(encodedPassword);
		tk.setQuyen(quyen);
		tk.setKhachhang(khachhang);
		taikhoanService.save(tk);

		return "redirect:/";
	}

//	@PostMapping("/users")
//	public String addUser(Khachhang kh,Model model) {
//		String KH="KH";
//		Khachhang khachhang2=khachhangService.getkhachhangLast();
//		System.out.println(khachhang2.getMakh().substring(2, 3));
//	   String h1=khachhang2.getMakh().replace("KH", "");
//	   Integer h2=Integer.parseInt(h1)+1;
//	   System.out.println(h2);
//		kh.setMakh(KH.concat(h2.toString()));
//		khachhangService.save(kh);
//		List<Khachhang> listkhachhang=khachhangService.getAllKhachhang();
//		model.addAttribute("listUsers", listkhachhang);
//		return "views/users";
//	}
//	@GetMapping("/user_new")
//	public String showAddUser(Model model) {
//		model.addAttribute("user", new Khachhang());
//		return "views/create_student";
//	}
	@GetMapping("/login_tk")
	public String listUsers(Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
		List<Khachhang> listkhachhang = khachhangService.getAllKhachhang();
		model.addAttribute("listUsers", listkhachhang);
		model.addAttribute("user", tk);
//		System.out.println(tk.getKhachhang().getMasothue());
//		session.setAttribute("mySessionAttribute", tk.getTentk());
		session.setAttribute("mySession", tk);
//		List<Hoadon> listhoadon=hoaDonService.getLayDSHD();
		if (tk.getQuyen().getTenquyen().equals("KHÁCH HÀNG")) {
			System.out.println(1);
			try {
				listmhdanhgia = apriori.Apriori(tk.getKhachhang().getMasothue());
				for(Mathang mathang : listmhdanhgia) {
					System.out.println(mathang.getMamh());
				}
				return "redirect:/";
			} catch (Exception e) {
				// TODO: handle exception
			}	

//			System.out.println(2);
			return "redirect:/";
		}

		return "redirect:/list_users";
	}
//    @GetMapping("/list_catogery/{nhan}")
//    public String listCatogery(Model model,@PathVariable String nhan ) {
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPricipalName = authentication.getName();
//		System.out.print(currentPricipalName);
//		if (!currentPricipalName.equals("anonymousUser")) {
//			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
//			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
//				currentPricipalName = "ADMIN";
//			} else {
//				currentPricipalName = "USER";
//			}
//		}
//		List<MathangDTO> listmhDTO = new ArrayList<MathangDTO>();
//		model.addAttribute("user", currentPricipalName);
//		List<Mathang> listmathang = mathangService.getMHByNhan(nhan);
//		List<Banggia> banggia2 = new ArrayList<Banggia>();
//		List<Mathang> listmathang2 = new ArrayList<Mathang>();
//		for (Mathang items : listmathang) {
//			banggia2.add(bangGiaService.getBanggiaLast(items.getMamh()));
////			System.out.println(items.getMamh());
//		}
//		for (Banggia gia : banggia2) {
//		}
//		for (Mathang items : listmathang) {
//			listmhDTO.add(mathangService.getmathanggia(items, bangGiaService.getBanggiaLast(items.getMamh())));
//		}
//		model.addAttribute("listmathang", listmhDTO);
//		model.addAttribute("gia", banggia2);
//    	return "views/items";
//    }
	@GetMapping("list_item_caocap")
	public String listItemCaoCap(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		System.out.print(currentPricipalName);
		if (!currentPricipalName.equals("anonymousUser")) {
			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
				currentPricipalName = "ADMIN";
			} else {
				currentPricipalName = "USER";
			}
		}
			List<MathangDTO> listmhDTO = new ArrayList<MathangDTO>();
			model.addAttribute("user", currentPricipalName);
			List<Mathang> listmathang = mathangService.getMHByNhan("2");
			List<Banggia> banggia2 = new ArrayList<Banggia>();
			List<Mathang> listmathang2 = new ArrayList<Mathang>();
			for (Mathang items : listmathang) {
				banggia2.add(bangGiaService.getBanggiaLast(items.getMamh()));
			}
			for (Banggia gia : banggia2) {
			}
			for (Mathang items : listmathang) {
				listmhDTO.add(mathangService.getmathanggia(items, bangGiaService.getBanggiaLast(items.getMamh()),hinhAnhService.getHinhAnhByMH(items.getMamh())));
			}
			model.addAttribute("listmathang", listmhDTO);
			model.addAttribute("gia", banggia2);
		return "views/items";
	}
	@GetMapping("/list_item_tamtrung")
	public String listItemTamtrung(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		System.out.print(currentPricipalName);
		if (!currentPricipalName.equals("anonymousUser")) {
			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
				currentPricipalName = "ADMIN";
			} else {
				currentPricipalName = "USER";
			}
		}
			List<MathangDTO> listmhDTO = new ArrayList<MathangDTO>();
			model.addAttribute("user", currentPricipalName);
			List<Mathang> listmathang = mathangService.getMHByNhan("1");
			List<Banggia> banggia2 = new ArrayList<Banggia>();
			List<Mathang> listmathang2 = new ArrayList<Mathang>();
			for (Mathang items : listmathang) {
				banggia2.add(bangGiaService.getBanggiaLast(items.getMamh()));
			}
			for (Banggia gia : banggia2) {
			}
			for (Mathang items : listmathang) {
				listmhDTO.add(mathangService.getmathanggia(items, bangGiaService.getBanggiaLast(items.getMamh()),hinhAnhService.getHinhAnhByMH(items.getMamh()) ));
			}
			model.addAttribute("listmathang", listmhDTO);
			model.addAttribute("gia", banggia2);
		return "views/items";
	}
	
	@GetMapping("/list_item_binhdan")
	public String listItemBinhdan(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		System.out.print(currentPricipalName);
		if (!currentPricipalName.equals("anonymousUser")) {
			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
				currentPricipalName = "ADMIN";
			} else {
				currentPricipalName = "USER";
			}
		}
			List<MathangDTO> listmhDTO = new ArrayList<MathangDTO>();
			model.addAttribute("user", currentPricipalName);
			List<Mathang> listmathang = mathangService.getMHByNhan("0");
			List<Banggia> banggia2 = new ArrayList<Banggia>();
			List<Mathang> listmathang2 = new ArrayList<Mathang>();
			for (Mathang items : listmathang) {
				banggia2.add(bangGiaService.getBanggiaLast(items.getMamh()));
			}
			for (Banggia gia : banggia2) {
			}
			for (Mathang items : listmathang) {
				listmhDTO.add(mathangService.getmathanggia(items, bangGiaService.getBanggiaLast(items.getMamh()),hinhAnhService.getHinhAnhByMH(items.getMamh()) ));
			}
			model.addAttribute("listmathang", listmhDTO);
			model.addAttribute("gia", banggia2);
		return "views/items";
	}
	@GetMapping("/list_items")
	public String listItems(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		System.out.print(currentPricipalName);
		if (!currentPricipalName.equals("anonymousUser")) {
			Taikhoan tk = taikhoanService.findByTaikhoan(currentPricipalName);
			if (tk.getQuyen().getTenquyen().equals("ADMIN")) {
				currentPricipalName = "ADMIN";
			} else {
				currentPricipalName = "USER";
			}
		}
			List<MathangDTO> listmhDTO = new ArrayList<MathangDTO>();
			model.addAttribute("user", currentPricipalName);
			List<Mathang> listmathang = mathangService.getAllMathang();
			List<Banggia> banggia2 = new ArrayList<Banggia>();
			List<Mathang> listmathang2 = new ArrayList<Mathang>();
			for (Mathang items : listmathang) {
				banggia2.add(bangGiaService.getBanggiaLast(items.getMamh()));
			}
			for (Banggia gia : banggia2) {
			}
			for (Mathang items : listmathang) {
				listmhDTO.add(mathangService.getmathanggia(items, bangGiaService.getBanggiaLast(items.getMamh()),hinhAnhService.getHinhAnhByMH(items.getMamh()) ));
			
			}
			System.out.println(listmhDTO.get(1).getHinhanh());
			model.addAttribute("listmathang", listmhDTO);
			model.addAttribute("gia", banggia2);
		return "views/items";
	}

	@GetMapping("user_edit/{makh}")
	public String editUser(Model model, @PathVariable String makh) {
		model.addAttribute("user", khachhangService.getKhachhangById(makh));
		return "views/edit_user";
	}

	@PostMapping("user_edit/{makh}")
	public String updateUser(Model model, Khachhang kh, @PathVariable String makh) {
		Khachhang exitkh = khachhangService.getKhachhangById(makh);
		exitkh.setHotenkh(kh.getHotenkh());
		exitkh.setSocmnd(kh.getSocmnd());
		exitkh.setEmail(kh.getEmail());
		khachhangService.save(exitkh);
		List<Khachhang> lisUsers = khachhangService.getAllKhachhang();
		model.addAttribute("listUsers", lisUsers);
		return "views/users";
	}

	@GetMapping("items_info/{mamh}")
	public String showitems(Model model, @PathVariable String mamh) {
		Mathang mathang = mathangService.getMHById(mamh);
		MathangDTO mathangDTO = new MathangDTO();
		mathangDTO = mathangService.getmathanggia(mathang, bangGiaService.getBanggiaLast(mamh),hinhAnhService.getHinhAnhByMH(mamh));
		List<Size> sizes = sizeService.getAllSize();
		model.addAttribute("sizes", sizes);
		model.addAttribute("mathang", mathangDTO);
		String listMHStr = getRecommendation(mamh);
		String tmp = listMHStr.replace("'", "");
		tmp = tmp.replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace(" ", "");
		model.addAttribute("listMathang", mathangService.getInListID(tmp));
//		Danhgia danhgia = new Danhgia();
//		model.addAttribute("danhgia", danhgia);
		return "views/item_detail_1";
	}
	
	@PostMapping("items_info/rating/{maMH}")
	public String danhGia(@RequestParam("starNumber")String starNumber,
			@PathVariable("maMH")String maMH) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPricipalName = authentication.getName();
		Danhgia danhgia = new Danhgia();
		danhgia.setDanhgia(Integer.parseInt(starNumber));
		Mathang myMH = mathangService.getMHById(maMH);
		danhgia.setMathang(myMH);
		Taikhoan myTK = taikhoanService.findByTaikhoan(currentPricipalName);
		danhgia.setTaikhoan(myTK);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		//
		String list = myTK.getKhachhang().getMakh() + "," + myMH.getMamh() + "," + starNumber + "," + timeStamp ;
		String tmp = saveRatingRecord(list);
		
		// luu vao database cho nay
		
		
		
		System.out.println("get danh gia");
		System.out.println(danhgia.getTaikhoan().getTentk() + " " + danhgia.getMathang().getMamh() + " " + danhgia.getDanhgia());
		
		return "redirect:/";
	}

	public String getRecommendation(String maMH) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			String cmd = "python C:\\Users\\ADMIN\\Downloads\\test\\PhatTrienHeThongThongMinh\\back-end\\demo4\\src\\main\\resources\\python\\test.py " + maMH;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);

				str = s;
			}

			// read any errors from the attempted command
//			System.out.println("Here is the standard error of the command (if any):\n");
//			while ((s = stdError.readLine()) != null) {
//				System.out.println(s);
//			}

			// System.exit(0);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return str;

	}
	public String saveRatingRecord(String red) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			String cmd = "python C:\\Users\\ADMIN\\Downloads\\test\\PhatTrienHeThongThongMinh\\back-end\\demo4\\src\\main\\resources\\python\\add-to-csv.py " + red;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);

				str = s;
			}

		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return str;

	}

	@GetMapping("shopping_cart")
	public String showcartshopping(HttpSession session) {
		OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
		session.setAttribute("order", orderDTO);
		return "views/bag_detail";
	}

	@GetMapping("add_item_cart/{mamh}")
	public String showCart(Model model, HttpServletRequest request, @PathVariable String mamh, HttpSession session) {
		int quantity = 1;
		Integer id = 1;
		Integer ma=1;
		String mamh2;
		if (mamh != null) {
			mamh2 = mamh;
			Mathang mathang = mathangService.getMHById(mamh2);
			MathangDTO mathangDTO = mathangService.getmathanggia(mathang, bangGiaService.getBanggiaLast(mamh2),hinhAnhService.getHinhAnhByMH(mamh2));
			if (mathangDTO != null) {
				if (request.getParameter("quantity") != null) {
					quantity = Integer.parseInt(request.getParameter("quantity"));
					System.out.println(request.getParameter("quantity"));
				}
				if (session.getAttribute("order") == null) {
					Taikhoan taikhoan = (Taikhoan) session.getAttribute("mySession");
					TaikhoanDTO taikhoanDTO = new TaikhoanDTO();
					taikhoanDTO.setTentk(taikhoan.getTentk());
					taikhoanDTO.setRole(taikhoan.getQuyen().getMaquyen());
					OrderDTO orderDTO = new OrderDTO();
					List<Item> items = new ArrayList<Item>();
					Item item = new Item();
					item.setId(id);
					item.setQuantity(quantity);
					item.setMathangDTO(mathangDTO);
					item.setGia(mathangDTO.getGia());
					items.add(item);
					orderDTO.setId(ma);
					orderDTO.setItems(items);
					orderDTO.setCustomer(taikhoanDTO);
					session.setAttribute("order", orderDTO);
				} else {
					OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
					List<Item> listitems = orderDTO.getItems();
//					System.out.println("1");
//					for (Item item : listitems) {
//						System.out.println(item.getMathangDTO().getMamh());
//					}					
					boolean check = false;
					for (Item item : listitems) {
						if (item.getMathangDTO().getMamh().equals(mathangDTO.getMamh())) {
							item.setQuantity(item.getQuantity() + quantity);
							item.setId(id + item.getId());
							check = true;
						}
					}
					if (check == false) {
						Item item = new Item();
						item.setQuantity(quantity);
						item.setMathangDTO(mathangDTO);
						item.setGia(mathangDTO.getGia());
						listitems.add(item);
						orderDTO.setId(ma+orderDTO.getId());
					}
					session.setAttribute("order", orderDTO);
				}
			}
			return "redirect:/shopping_cart";
		}
		return "redirect:/shopping_cart";
	}

	@PostMapping("shopping_cart/update")
	public String updateshoppingcart(Model model, HttpSession session, @RequestParam("productId") String productid,
			@RequestParam("quantity") Integer quantity) {
		Mathang mathang = mathangService.getMHById(productid);
		MathangDTO mathangDTO = mathangService.getmathanggia(mathang, bangGiaService.getBanggiaLast(productid),hinhAnhService.getHinhAnhByMH(productid));
		if (session.getAttribute("order") == null) {
			OrderDTO orderDTO = new OrderDTO();
			List<Item> items = new ArrayList<Item>();
			Item item = new Item();
			item.setQuantity(quantity);
			item.setMathangDTO(mathangDTO);
			item.setGia(mathangDTO.getGia());
			items.add(item);
			orderDTO.setItems(items);
			session.setAttribute("order", orderDTO);
		} else {
			OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
			List<Item> listitems = orderDTO.getItems();

			boolean check = false;
			Integer sum = 0;
			for (int i = 0; i < listitems.size(); i++) {
				if (listitems.get(i).getMathangDTO().getMamh().equals(mathangDTO.getMamh())) {
					System.out.println(listitems.get(i).getMathangDTO().getMamh());
					listitems.get(i).setQuantity(quantity);
					check = true;
				}
				sum += listitems.get(i).getQuantity() * listitems.get(i).getGia();
			}
			System.out.println(sum);
			if (check == false) {
				Item item = new Item();
				item.setQuantity(quantity);
				item.setMathangDTO(mathangDTO);
				item.setGia(mathangDTO.getGia());
				listitems.add(item);
			}
			session.setAttribute("order", orderDTO);
		}
		return "redirect:/shopping_cart";
	}

	@GetMapping("remove_item/{mamh}")
	public String removeItem(Model mode, @PathVariable String mamh, HttpSession session) {
		int quantity = 1;
//		Integer id=1;
		String mamh2;
		if (mamh != null) {
			mamh2 = mamh;
			Mathang mathang = mathangService.getMHById(mamh2);
			MathangDTO mathangDTO = mathangService.getmathanggia(mathang, bangGiaService.getBanggiaLast(mamh2),hinhAnhService.getHinhAnhByMH(mamh2));
			if (mathangDTO != null) {
				if (session.getAttribute("order") == null) {
					OrderDTO orderDTO = new OrderDTO();
					List<Item> items = new ArrayList<Item>();
					Item item = new Item();
//					item.setId(id);
					item.setQuantity(quantity);
					item.setMathangDTO(mathangDTO);
					item.setGia(mathangDTO.getGia());
					items.add(item);
					orderDTO.setItems(items);
					session.setAttribute("order", orderDTO);
				} else {
					OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
					List<Item> listitems = orderDTO.getItems();
					boolean check = false;
//					for(Item item:listitems) {
//						if (item.getMathangDTO().getMamh().equals(mathangDTO.getMamh())) {
//							System.out.print(item.getMathangDTO().getMamh());
//							listitems.remove(0);
//							check=true;
//						}
//					}
					for (int i = 0; i < listitems.size(); i++) {
						if (listitems.get(i).getMathangDTO().getMamh().equals(mathangDTO.getMamh())) {
							System.out.println(listitems.get(i).getMathangDTO().getMamh());
							listitems.remove(i);
							check = true;
						}

					}
					if (check == false) {
						Item item = new Item();
						item.setQuantity(quantity);
						item.setMathangDTO(mathangDTO);
						item.setGia(mathangDTO.getGia());
						listitems.add(item);
					}
					session.setAttribute("order", orderDTO);
				}
			}
			return "redirect:/shopping_cart";
		}
		return "redirect:/shopping_cart";
	}

	@PostMapping("/insert_items_cart")
	public String insertItemsCart(Model model, HttpSession session) {
		OrderDTO orderDTO = (OrderDTO) session.getAttribute("order");
		String tentk = orderDTO.getCustomer().getTentk();
		List<Item> listitems = orderDTO.getItems();
//		List<Mathang> listmathanglast = mathangService.getMathangLast();
//		   String MH="MH";
//		   Integer index = listmathanglast.size();
//		   String h1=listmathanglast.get(index-1).getMamh().replace("MH", "");
//		   Integer h2=Integer.parseInt(h1)+1;
//		   mh.setMamh(MH.concat(h2.toString()));
		List<Phieudat> listPD=phieuDatService.getLastPD();
		String PD="PD";
		Integer index=listPD.size();
		String h1=listPD.get(index-1).getMapd().replace("PD", "");
		Integer h2=index.parseInt(h1)+1;
		Taikhoan taikhoan = taikhoanService.findByTaikhoan(tentk);
		Phieudat phieudat = new Phieudat();
		phieudat.setMapd(PD.concat(h2.toString()));
		phieudat.setKhachhang(taikhoan.getKhachhang());
		phieudat.setNgaydat(new Date());
		phieuDatService.save(phieudat);
		Ctpd ctpd=new Ctpd();
		CtpdId ctpdId=new CtpdId();
		for (Item item : listitems) {
			ctpdId.setMamh(item.getMathangDTO().getMamh());
			ctpdId.setMapd(phieudat.getMapd());
			ctpd.setId(ctpdId);
			ctpd.setSoluong(item.getQuantity());
			chittietPDRepository.save(ctpd);
		}

		
		return "redirect:/";
	}

//	@GetMapping("/list_users/{makh}")
//	public String deleteUser(Model model,@PathVariable String makh) {
//		khachhangService.deleteKhachhangById(makh);
//		return "redirect:/list_users";
//	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("mySession");
		return "views/index";
	}
	@GetMapping("/order_history")
	public String showhistory(Model model,HttpSession session) {
		Taikhoan taikhoan = (Taikhoan) session.getAttribute("mySession");
		String makh=taikhoan.getKhachhang().getMakh();
		System.out.println(makh);
		List<Phieudat> listphietdat=phieuDatService.getPDByKH(makh);
//		OrderDTO orderDTO = new OrderDTO();
//		List<Item> items = new ArrayList<Item>();
//		Item item = new Item();
//		item.setQuantity(quantity);
//		item.setMathangDTO(mathangDTO);
//		item.setGia(mathangDTO.getGia());
//		items.add(item);
//		orderDTO.setItems(items);
		List<Ctpd> lisctPD=chittietPDRepository.getctPD1(makh);
		for(Ctpd item: lisctPD) {
			System.out.println(item.getPhieudat().getNgaydat());
		}
		model.addAttribute("history", lisctPD);
//		Orderhistory orderhistory=new Orderhistory();
		session.removeAttribute("order");
		return "views/order_history";
	}
	
}
