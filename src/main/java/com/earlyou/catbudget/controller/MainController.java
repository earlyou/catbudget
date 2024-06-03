package com.earlyou.catbudget.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earlyou.catbudget.biz.ListinfoBiz;
import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.ListinfoVO;
import com.earlyou.catbudget.vo.PaymentVO;
import com.earlyou.catbudget.vo.UserinfoVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserinfoBiz ubiz;

	@Autowired
	PaymentBiz pbiz;

	@Autowired
	ListinfoBiz lbiz;

	@GetMapping("/")
	public String main(Model m, HttpSession s, RedirectAttributes r,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "ipp", defaultValue = "10") int ipp) {

		int sin = (page - 1) * ipp;
		int length = 0;
		List<PaymentVO> list = null;
		String uid = "";

		if (s.getAttribute("uid") == null) {

			// m.addAttribute와 비슷하지만 POST방식이며 1회성이라 새로고침하면 데이터 소멸
			r.addFlashAttribute("v", "n");

			return "redirect:/login";
		} else {
			uid = s.getAttribute("uid").toString();
			if (startdate == null || startdate == "" || enddate == null || enddate == "") {
				// login -> main
				ListinfoVO listlengthinfo = new ListinfoVO(uid);
				ListinfoVO listinfo = new ListinfoVO(uid, sin, ipp);

				try {
					list = lbiz.getbypage(listinfo);
					length = lbiz.getlength(listlengthinfo);
				} catch (Exception e) {
					m.addAttribute("main", "auth/login");
					return "index";
				}

			} else {
				// main -> main
				ListinfoVO listlengthinfo = new ListinfoVO(uid, startdate, enddate);
				ListinfoVO listinfo = new ListinfoVO(uid, startdate, enddate, sin, ipp);

				try {
					list = lbiz.getbydaterange(listinfo);
					length = lbiz.getlengthbydaterange(listlengthinfo);
				} catch (Exception e) {
					m.addAttribute("main", "auth/login");
					return "index";
				}

			}
		}

//		System.out.println("startdate: " + startdate);
//		System.out.println("enddate: " + enddate);
//		System.out.println("page: " + page);
//		System.out.println("ipp: " + ipp);
//		System.out.println("sin: " + sin);
//		System.out.println("list: " + list);
//		System.out.println("length: " + length);
//		System.out.println("maxpage: " + (int) Math.ceil((double) length / ipp));

		m.addAttribute("startdate", startdate);
		m.addAttribute("enddate", enddate);
		m.addAttribute("page", page);
		m.addAttribute("ipp", ipp);
		m.addAttribute("sin", sin);
		m.addAttribute("list", list);
		m.addAttribute("length", length);
		m.addAttribute("maxpage", (int) Math.ceil((double) length / ipp));
		m.addAttribute("main", "main/main");
		return "index";
	}

	@GetMapping("/login")
	public String login(Model m, HttpSession s) {

		if (s.getAttribute("uid") == null) {
			if (m.getAttribute("v") == null) {
				m.addAttribute("v", "n");
			}
		} else {
			return "redirect:/";
		}

		m.addAttribute("main", "auth/login");
		return "index";
	}

	@PostMapping("/loginimpl")
	public String login(Model m, @RequestParam("uid") String uid, @RequestParam("pwd") String pwd, HttpSession s,
			RedirectAttributes r) {

		UserinfoVO userinfo = null;

		try {
			userinfo = ubiz.get(uid);
			if (userinfo != null) {
				if (userinfo.getPwd().equals(pwd) && userinfo.getDel().equals(false)) {
					s.setAttribute("uid", userinfo.getUid());
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			r.addFlashAttribute("v", "f");
			return "redirect:/login";
//			return new RedirectView("/login");
		}
		r.addAttribute("tt", "test");
		r.addAttribute("test1", "test1");
//		return new RedirectView("/catbudget");
		return "redirect:/";
	}

	private static String UPLOAD_DIR = "src\\main\\resources\\static\\img\\";
	private static String UPLOAD_staitc = "src\\main\\resources\\static\\";

	@PostMapping("/add")
	public String add(HttpSession s, RedirectAttributes r,
			@RequestParam(value = "regdate", required = false) String regdate,
			@RequestParam(value = "detail", defaultValue = "") String detail,
			@RequestParam(value = "price", defaultValue = "0") int price,
			@RequestParam(value = "addfile", required = false) MultipartFile mf,
			@RequestParam(value = "memo", defaultValue = "") String memo) {

		String uid = s.getAttribute("uid").toString();
		List<PaymentVO> l = null;
		PaymentVO c = new PaymentVO(uid, regdate);

		try {
			l = pbiz.getbydate(c);
			int seq = l.size();

			// 업로드 방법1
			byte[] bytes = mf.getBytes();
			Path path = Paths.get(UPLOAD_DIR + regdate + "(" + seq + ")" + "_" + mf.getOriginalFilename());
			Files.write(path, bytes);

			// 업로드 방법2
//			String filename = mf.getOriginalFilename();
//			byte[] bytes = mf.getBytes();
//			String path = UPLOAD_DIR + regdate + "(" + seq + ")" + "_" + filename;
//			FileOutputStream fos = new FileOutputStream(path);
//			fos.write(bytes);
//			fos.close();

			PaymentVO n = new PaymentVO(uid, regdate, seq, detail, price,
					"img/" + regdate + "(" + seq + ")" + "_" + mf.getOriginalFilename(), memo);
			pbiz.register(n);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return "redirect:/";
	}

	@PostMapping("/mdfy")
	public String mdfy(HttpSession s, 
			@RequestParam(value = "num", required = false) int num,
			@RequestParam(value = "regdate", required = false) String regdate,
			@RequestParam(value = "detail", defaultValue = "") String detail,
			@RequestParam(value = "price", defaultValue = "0") int price,
			@RequestParam(value = "addfile", required = false) MultipartFile mf,
			@RequestParam(value = "memo", defaultValue = "") String memo) {

		String uid = s.getAttribute("uid").toString();
		List<PaymentVO> l = null;
		PaymentVO c = new PaymentVO(uid, regdate);
		PaymentVO o = null;
		int seq = 0;
		
		try {
			o = pbiz.get(num);
			l = pbiz.getbydate(c);
			seq = l.size();
			
			if (mf == null) {
				System.out.println("file not changed");
				if (o.getRegdate().toString() == regdate) {
					System.out.println("regdate not changed");
					System.out.println(o.getRegdate() + " == " + regdate);
					PaymentVO n = new PaymentVO(num, uid, regdate, o.getSeq(), detail, price, o.getPic(), memo);
					pbiz.modify(n);
				} else {
					System.out.println("regdate changed");
					System.out.println(o.getRegdate() + " != " + regdate);
					String pic = "img/" + regdate + "(" + seq + ")" + "_" + o.getPic().substring(4);
					File file = new File(UPLOAD_staitc + o.getPic());
					File newfile = new File(UPLOAD_staitc + pic);
					file.renameTo(newfile);
					PaymentVO n = new PaymentVO(num, uid, regdate, seq, detail, price, pic, memo);
					pbiz.modify(n);
				}
			} else {
				System.out.println("file changed");
				File file = new File(UPLOAD_staitc + o.getPic());
				file.delete();
				byte[] bytes = mf.getBytes();
				Path path = Paths.get(UPLOAD_DIR + regdate + "(" + seq + ")" + "_" + mf.getOriginalFilename());
				Files.write(path, bytes);
				PaymentVO n = new PaymentVO(uid, regdate, seq, detail, price,
						"img/" + regdate + "(" + seq + ")" + "_" + mf.getOriginalFilename(), memo);
				pbiz.modify(n);
			}
		} catch (Exception e) {
			System.out.println("Exception Occured!");
			return "redirect:/";
		}
		return "redirect:/";
	};
}
