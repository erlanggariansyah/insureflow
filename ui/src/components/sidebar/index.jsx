import Links from "./components/Links";
import routes from "routes.js";
import logoShort from "../../assets/img/logo_short.png";

const Sidebar = ({ open, onClose }) => {
  return (
    <div
      className={`sm:none duration-300 linear fixed !z-50 flex min-h-full w-[280px] flex-col bg-gradient-to-b from-white to-[#f4f7fe] pb-10 shadow-2xl shadow-gray-200/40 transition-all dark:!bg-navy-800 dark:from-navy-900 dark:to-navy-800 dark:text-white dark:shadow-navy-900 md:!z-50 lg:!z-50 xl:!z-0 border-r border-gray-100 dark:border-navy-700 ${open ? "translate-x-0" : "-translate-x-96"
        }`}
    >
      <div className={`mx-[32px] mt-[40px] mb-[20px] flex items-center gap-4`}>
        <div className="flex h-11 w-11 items-center justify-center rounded-xl bg-gradient-to-br from-blue-600 to-blue-400 shadow-xl shadow-blue-500/30">
          <img src={logoShort} alt="LifeConsole Logo" className="h-7 w-7 object-contain" />
        </div>
        <div className="flex flex-col">
          <div className="font-poppins text-[24px] font-extrabold uppercase leading-tight tracking-wide text-navy-700 dark:text-white">
            Life<span className="font-light text-blue-500">Console</span>
          </div>
          <p className="text-[9px] font-bold tracking-[0.2em] text-gray-400 uppercase mt-0.5">By OpenLife</p>
        </div>
      </div>

      <div className="mx-6 mb-6 mt-2 h-[1px] bg-gradient-to-r from-transparent via-gray-200 to-transparent dark:via-navy-700" />

      <div className="mb-auto mt-[5px] flex flex-col px-3 overflow-y-auto pb-10 custom-scrollbar">
        <ul className="flex flex-col gap-0.5">
          <Links routes={routes} />
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;
