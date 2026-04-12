import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import DashIcon from "components/icons/DashIcon";
import { MdKeyboardArrowDown, MdKeyboardArrowRight } from "react-icons/md";

export function SidebarLinks(props) {
  let location = useLocation();
  const { routes } = props;
  const [openCategory, setOpenCategory] = useState("Master Data");

  // Check if a specific route configuration is active
  const activeRoute = (routeName) => {
    return location.pathname.includes(routeName);
  };

  // Pre-process the flat routes array into {category, items: []} schema
  const groupedRoutes = [];
  let currentGroup = { category: "General", items: [] };

  routes.forEach(route => {
    if (route.isCategory) {
      if (currentGroup.items.length > 0 || currentGroup.category !== "General") {
        groupedRoutes.push(currentGroup);
      }
      currentGroup = { category: route.name, items: [] };
    } else {
      currentGroup.items.push(route);
    }
  });
  if (currentGroup.items.length > 0) {
    groupedRoutes.push(currentGroup);
  }

  const createLinks = (groupList) => {
    return groupList.map((group, groupIndex) => {
      
      // Standalone menus (e.g. Dashboard)
      if (group.category === "General") {
        return group.items.map((route, idx) => {
          if (route.hideInSidebar || route.layout === "/auth") return null;
          
          const isActive = activeRoute(route.path);
          return (
            <Link key={`general-${idx}`} to={route.layout + "/" + route.path}>
              <div className="relative mb-2 flex flex-col hover:cursor-pointer">
                <li
                  className={`my-[1px] flex cursor-pointer items-center justify-between rounded-xl px-4 py-[10px] transition-all duration-300 ${
                    isActive
                      ? "bg-gradient-to-r from-blue-600 to-brand-400 shadow-lg shadow-blue-500/20 translate-x-1"
                      : "hover:bg-blue-50/50 hover:translate-x-2 dark:hover:bg-navy-700/50"
                  }`}
                >
                  <div className="flex items-center gap-4">
                    <span className={`${isActive ? "text-white" : "text-gray-500 dark:text-gray-400"}`}>
                      {route.icon ? route.icon : <DashIcon />}
                    </span>
                    <p className={`leading-1 flex text-[13px] ${isActive ? "font-bold text-white tracking-wide" : "font-semibold text-navy-700 dark:text-white"}`}>
                      {route.name}
                    </p>
                  </div>
                </li>
              </div>
            </Link>
          );
        });
      }

      // Grouped menus (Accordion Folders)
      const isOpen = openCategory === group.category;
      
      // Determine if a child inside is currently active
      const hasActiveChild = group.items.some(r => activeRoute(r.path));

      return (
        <div key={`group-${groupIndex}`} className="mb-2 flex flex-col">
          {/* Accordion header */}
          <div 
            onClick={() => setOpenCategory(isOpen ? "" : group.category)}
            className={`flex cursor-pointer items-center justify-between rounded-xl px-4 py-[12px] transition-all duration-300 ${
              isOpen || hasActiveChild 
                ? "bg-gray-50 dark:bg-navy-700/40" 
                : "hover:bg-blue-50/30 dark:hover:bg-navy-700/30"
            }`}
          >
            <div className="flex items-center gap-3">
               <h4 className={`text-[11px] uppercase tracking-widest ${hasActiveChild ? 'font-black text-blue-600 dark:text-blue-400' : 'font-bold text-gray-500 dark:text-gray-400'}`}>
                  {group.category}
               </h4>
            </div>
            {isOpen ? <MdKeyboardArrowDown className="text-gray-400" /> : <MdKeyboardArrowRight className="text-gray-400" />}
          </div>
          
          {/* Accordion content */}
          <div className={`flex flex-col gap-0.5 overflow-hidden transition-all duration-500 ${isOpen ? "max-h-[800px] opacity-100 mt-2 mb-3" : "max-h-0 opacity-0"}`}>
             <div className="flex flex-col gap-1 ml-6 pl-2 border-l-2 border-gray-100 dark:border-navy-700/60">
               {group.items.map((route, childIdx) => {
                  if (route.hideInSidebar || route.layout === "/auth") return null;
                  const isActive = activeRoute(route.path);
                  return (
                    <Link key={`child-${childIdx}`} to={route.layout + "/" + route.path}>
                      <div className={`group flex items-center justify-between rounded-lg px-3 py-[9px] transition-all duration-300 ${
                         isActive
                           ? "bg-gradient-to-r from-blue-600 to-brand-400 shadow-md shadow-blue-500/20 translate-x-1"
                           : "hover:bg-blue-50/50 hover:translate-x-1 dark:hover:bg-navy-700/50"
                      }`}>
                        <div className="flex items-center gap-3">
                          <span className={`transition-transform duration-200 group-hover:scale-110 ${isActive ? "text-white" : "text-gray-400 dark:text-gray-500"}`}>
                            {route.icon ? route.icon : <DashIcon />}
                          </span>
                          <p className={`text-[12px] whitespace-nowrap ${isActive ? "font-bold text-white tracking-wide" : "font-semibold text-gray-500 dark:text-gray-400"}`}>
                            {route.name}
                          </p>
                        </div>
                      </div>
                    </Link>
                  );
               })}
             </div>
          </div>
        </div>
      );
    });
  };

  return createLinks(groupedRoutes);
}

export default SidebarLinks;
